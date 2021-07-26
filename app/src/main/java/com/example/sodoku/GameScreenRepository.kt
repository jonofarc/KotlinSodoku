package com.example.sodoku

import android.app.Activity
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


interface GameScreenRepository {

    fun setLvl()
    fun setUI(sudokuRV: RecyclerView)
}

class GameScreenImpl(private val activity: Activity) : GameScreenRepository {
    private var difficultyLvl = 1
    val sudokuMatrix: MutableList<Int> = mutableListOf()

    override fun setLvl() {

        val dificultyLvlExtra = activity.intent.extras?.getInt(GameScreenActivity.GAME_DIFICULTY, 1) ?: 1
        difficultyLvl = dificultyLvlExtra
        Toast.makeText(activity,"Difficulty set to $difficultyLvl", Toast.LENGTH_SHORT).show()
        createSodokuMatrix()
    }

    override fun setUI(sudokuRV: RecyclerView) {

        setSudokuGameRecyclerViewAdapter(sudokuRV)
    }

    private fun setSudokuGameRecyclerViewAdapter(sudokuRV: RecyclerView) {
        sudokuRV.setHasFixedSize(true)

        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels*0.9


        val adapter = SudokuGameRecyclerViewAdapter(sudokuMatrix,(width).toInt(), (width).toInt()){ cellPossition ->
            Toast.makeText(activity, "$cellPossition", Toast.LENGTH_SHORT).show()
        }


        val numberOfColumns = 9
        val gridLayoutManager = object : GridLayoutManager(activity, numberOfColumns){ override fun canScrollVertically(): Boolean { return false } }


        sudokuRV.layoutManager = gridLayoutManager
        sudokuRV.adapter = adapter


    }


    private fun createSodokuMatrix() {
        //TODO create actual values
        sudokuMatrix.addAll(1..81)
    }
}
