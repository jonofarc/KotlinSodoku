package com.example.sodoku

import android.app.Activity
import android.content.res.ColorStateList
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


interface GameScreenRepository {

    fun setLvl()
    fun setUI(
        sudokuRV: RecyclerView,
        debugCorrectCells: TextView,
        gameCompletedCl: ConstraintLayout
    )
    fun setCurrentValue(i: Int)
    fun setValuesColor(valuesLl1: LinearLayout, colorStateList: ColorStateList?)
}

class GameScreenImpl( val activity: Activity) : GameScreenRepository {

    companion object {
        val TAG: String = GameScreenImpl::class.java.simpleName+"_TAG"
    }
    private val SUDOKU_SIZE = 81
    private var difficultyLvl = 1
    private val sudokuMatrix: MutableList<Int> = mutableListOf()
    private lateinit var adapter: SudokuGameRecyclerViewAdapter

    val horizontalCells = mutableListOf(
        mutableListOf(0,1,2,3,4,5,6,7,8),
        mutableListOf(9,10,11,12,13,14,15,16,17),
        mutableListOf(18,19,20,21,22,23,24,25,26),
        mutableListOf(27,28,29,30,31,32,33,34,35),
        mutableListOf(36,37,38,39,40,41,42,43,44),
        mutableListOf(45,46,47,48,49,50,51,52,53),
        mutableListOf(54,55,56,57,58,59,60,61,62),
        mutableListOf(63,64,65,66,67,68,69,70,71),
        mutableListOf(72,73,74,75,76,77,78,79,80),
    )
    val verticalCells = mutableListOf(
        mutableListOf(0,9,18,27,36,45,54,63,72),
        mutableListOf(1,10,19,28,37,46,55,64,73),
        mutableListOf(2,11,20,29,38,47,56,65,74),
        mutableListOf(3,12,21,30,39,48,57,66,75),
        mutableListOf(4,13,22,31,40,49,58,67,76),
        mutableListOf(5,14,23,32,41,50,59,68,77),
        mutableListOf(6,15,24,33,42,51,60,69,78),
        mutableListOf(7,16,25,34,43,52,61,70,79),
        mutableListOf(8,17,26,35,44,53,62,71,80),
    )

    val groupCells = mutableListOf(
        mutableListOf(0,1,2,9,10,11,18,19,20),
        mutableListOf(3,4,5,12,13,14,21,22,23),
        mutableListOf(6,7,8,15,16,17,24,25,26),
        mutableListOf(27,28,29,36,37,38,45,46,47),
        mutableListOf(30,31,32,39,40,41,48,49,50),
        mutableListOf(33,34,35,42,43,44,51,52,53),
        mutableListOf(54,55,56,63,64,65,72,73,74),
        mutableListOf(57,58,59,66,67,68,75,76,77),
        mutableListOf(60,61,62,69,70,71,78,79,80),
    )

    override fun setLvl() {

        val dificultyLvlExtra = activity.intent.extras?.getInt(GameScreenActivity.GAME_DIFFICULTY, 1) ?: 1
        difficultyLvl = dificultyLvlExtra
        Toast.makeText(activity,"Difficulty set to $difficultyLvl", Toast.LENGTH_SHORT).show()
        createSudokuMatrix()
    }

    override fun setUI(
        sudokuRV: RecyclerView,
        debugCorrectCells: TextView,
        gameCompletedCl: ConstraintLayout
    ) {

        setSudokuGameRecyclerViewAdapter(sudokuRV, debugCorrectCells, gameCompletedCl)

    }

    override fun setCurrentValue(currentValue: Int) {
        adapter?.currentSetValue = currentValue
    }

    override fun setValuesColor(ll: LinearLayout, color: ColorStateList?) {
        ll.children.forEach {
            it.backgroundTintList = color
        }
    }


    private fun setSudokuGameRecyclerViewAdapter(
        sudokuRV: RecyclerView,
        debugCorrectCells: TextView,
        gameCompletedCl: ConstraintLayout
    ) {
        sudokuRV.setHasFixedSize(true)

        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels*0.9

        val hiddenValues = hideSudokuValues()

        adapter = SudokuGameRecyclerViewAdapter(sudokuMatrix, hiddenValues,(width).toInt(), (width).toInt()){ cellPossition ->

            adapter?.pertinentCells?.clear()
            debugCorrectCells.text = adapter?.correctCells.toString()
            if(adapter?.correctCells ?: 0 >= SUDOKU_SIZE){
                gameCompletedCl.visibility = View.VISIBLE
            }

            // set color to pertinent cell for selected cell

            //horizontal check
            horizontalCells.forEach { horizontalLine ->

                if(horizontalLine.contains(cellPossition)){
                    adapter?.pertinentCells?.addAll(horizontalLine)
                }

            }



            // vertical check

            verticalCells.forEach { verticalLine ->

                if(verticalLine.contains(cellPossition)){
                    adapter?.pertinentCells?.addAll(verticalLine)
                }

            }


            //groupCheck

            groupCells.forEach { group ->

                if(group.contains(cellPossition)){
                    adapter?.pertinentCells?.addAll(group)
                }

            }

            adapter?.notifyDataSetChanged()

        }
        debugCorrectCells.text = adapter?.correctCells.toString()

        val numberOfColumns = 9
        val gridLayoutManager = object : GridLayoutManager(activity, numberOfColumns){ override fun canScrollVertically(): Boolean { return false } }


        sudokuRV.layoutManager = gridLayoutManager
        sudokuRV.adapter = adapter
        adapter.correctCells = 81-difficultyLvl



    }

    private fun hideSudokuValues(): MutableList<Int> {

        val hiddenValues = mutableListOf<Int>()



        for(i in 1 .. difficultyLvl){

            var newNumberFound = false
            while (!newNumberFound){

                val randomInteger = (0..SUDOKU_SIZE).shuffled().first()

                if(!hiddenValues.contains(randomInteger)){
                    hiddenValues.add(randomInteger)
                    newNumberFound=true
                }



            }


        }

        return hiddenValues
    }

    //this could probably be its own class to be reusable
    private fun createSudokuMatrix() {

        //Initialise sudokuMatrix
        sudokuMatrix.addAll(10..90)

        // insert real values any value above 9 is not a valid value
        var hardResetTries = 0
        var softResetTries =0
        var i = 0
        while( i <= 80) {
            var horizontalCheck = false
            var verticalCheck = false
            var groupCheck = false
            var randomInteger = -1
            while (!horizontalCheck || !verticalCheck || !groupCheck){
                randomInteger = (1..9).shuffled().first()
                //hard and soft reset for when random numbers are creating imposible sudokus
                hardResetTries++
                softResetTries++
                var repeatedNumber = false

                //horizontal check
                horizontalCells.forEach { horizontalLine ->

                    if(horizontalLine.contains(i)){
                        horizontalLine.forEach{ cellIndex ->

                            if(sudokuMatrix[cellIndex] == randomInteger){
                                repeatedNumber = true
                            }
                        }
                    }

                }

                if(!repeatedNumber){
                    horizontalCheck = true
                }

                // vertical check

                verticalCells.forEach { verticalLine ->

                    if(verticalLine.contains(i)){
                        verticalLine.forEach{ cellIndex ->

                            if(sudokuMatrix[cellIndex] == randomInteger){
                                repeatedNumber = true
                            }
                        }
                    }

                }

                if(!repeatedNumber){
                    verticalCheck = true
                }

                //groupCheck

                groupCells.forEach { group ->

                    if(group.contains(i)){
                        group.forEach{ cellIndex ->

                            if(sudokuMatrix[cellIndex] == randomInteger){
                                repeatedNumber = true
                            }
                        }
                    }

                }

                if(!repeatedNumber){
                    groupCheck = true
                }


                //soft reset removes the last 9 cells and tries to redo them again this is to prevent unsolvable sudoku from being created
                if(softResetTries >60){

                    Log.d(TAG, "doing softReset on i=$i" )
                    i -= 9
                    //check i never goes below 0
                    if(i<9){
                        i = 9
                    }
                    softResetTries = 0
                    sudokuMatrix[i] =-1
                    sudokuMatrix[i+1] =-1
                    sudokuMatrix[i+2] =-1
                    sudokuMatrix[i+3] =-1
                    sudokuMatrix[i+4] =-1
                    sudokuMatrix[i+5] =-1
                    sudokuMatrix[i+6] =-1
                    sudokuMatrix[i+7] =-1
                    sudokuMatrix[i+8] =-1
                }
                // hard reset in case the previous 10+ soft reset didnt solve the problem of unsolvable sudokus it reset the whole sudoku back to square 1
                if(hardResetTries > 600){
                    i = 0
                    Log.d(TAG, "doing hardreset" )
                    sudokuMatrix.clear()
                    sudokuMatrix.addAll(10..90)
                    hardResetTries = 0
                }
            }


            sudokuMatrix[i]= randomInteger
            i++
            softResetTries = 0
            hardResetTries = 0



        }

    }
}
