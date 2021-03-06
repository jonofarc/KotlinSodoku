package com.jonofarc.sudoku

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jonofarc.sodoku.toPixel
import kotlinx.android.synthetic.main.cell_layout.view.*


class SudokuGameRecyclerViewAdapter(
    private val sudokuValues: MutableList<Int>,
    private val displaySudokuMatrix: MutableList<Int>,
    private val hiddenValues: MutableList<Int>,
    private val width: Int,
    private val height: Int,
    val cellClick: (Any) -> Unit
) : RecyclerView.Adapter<SudokuGameRecyclerViewAdapter.ViewHolder>() {


    val pertinentCells: MutableList<Int> = mutableListOf()
    var correctCells = 0
    var selectedCell = -1
    var currentSetValue = -1
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = sudokuValues.size


    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val params = holder.cellRoot.layoutParams

        params.height = height / 9
        params.width = width / 9
        holder.cellRoot.layoutParams = params
        holder.cellRoot.setBackgroundColor(ColorUtils.cellBorderColor)



        if (hiddenValues.contains(position)) {
            if (displaySudokuMatrix[position] == sudokuValues[position]) {
                holder.cellValue.setTextColor(ColorUtils.userInputsCorrectTextColor)
            } else {
                holder.cellValue.setTextColor(ColorUtils.userInputsTextColor)
            }

        } else {
            holder.cellValue.setTextColor(ColorUtils.textColor)
        }
        if ((1..9).contains(displaySudokuMatrix[position])) {
            holder.cellValue.text = displaySudokuMatrix[position].toString()
        } else {
            holder.cellValue.text = ""
        }


        holder.cellRoot.setOnClickListener {
            cellClick(position)
        }
        holder.cellBorderStart.setBackgroundColor(ColorUtils.sudokuBorderColor)
        holder.cellBorderEnd.setBackgroundColor(ColorUtils.sudokuBorderColor)
        holder.cellBorderTop.setBackgroundColor(ColorUtils.sudokuBorderColor)
        holder.cellBorderBottom.setBackgroundColor(ColorUtils.sudokuBorderColor)


        holder.cellBorderStart.visibility = View.GONE
        holder.cellBorderEnd.visibility = View.GONE
        holder.cellBorderTop.visibility = View.GONE
        holder.cellBorderBottom.visibility = View.GONE

        //Show extra borders if needed
        //show extra topBorder
        if ((27..35).contains(position) || (54..62).contains(position)) {
            holder.cellBorderTop.visibility = View.VISIBLE
        }
        //show extra BottomBorder
        if ((18..26).contains(position) || (45..53).contains(position)) {
            holder.cellBorderBottom.visibility = View.VISIBLE
        }
        //show extra StartBorder
        val startBorders =
            listOf(3, 12, 21, 30, 39, 48, 57, 66, 75, 6, 15, 24, 33, 42, 51, 60, 69, 78)
        if (startBorders.contains(position)) {
            holder.cellBorderStart.visibility = View.VISIBLE
        }
        //show extra EndBorder
        val endBorders =
            listOf(2, 11, 20, 29, 38, 47, 56, 65, 74, 5, 14, 23, 32, 41, 52, 59, 68, 77)
        if (endBorders.contains(position)) {
            holder.cellBorderEnd.visibility = View.VISIBLE
        }

        if (pertinentCells.contains(position)) {

            holder.cellValue.setBackgroundColor(ColorUtils.pertinentCellBackGroundColor)

            if (!hiddenValues.contains(position)) {
                holder.cellValue.setBackgroundColor(ColorUtils.pertinentCellBackGroundColor)
            }

        } else {
            holder.cellValue.setBackgroundColor(ColorUtils.cellBackGroundColor)
        }

        //highlight all selected cells numbers (ex: if a 2 is selcted highlight all the number 2)
        if ((0..80).contains(selectedCell)) {
            if (displaySudokuMatrix[selectedCell] == displaySudokuMatrix[position]) {
                holder.cellValue.setTextColor(ColorUtils.selectedValuesColor)
                holder.cellValue.setTypeface(null, Typeface.BOLD)


                holder.cellValue.setPadding(0,0,0,0)

            }
        }else{
            holder.cellValue.setTypeface(null, Typeface.NORMAL)

            holder.cellValue.setPadding(3.toPixel(context),3.toPixel(context),3.toPixel(context),3.toPixel(context))
        }


    }

    fun checkCorrectValue(oldValue: String, newValue: String, position: Int): Boolean {
        var correctCell = false
        if (oldValue == sudokuValues[position].toString()) {
            correctCells--
        }

        if (newValue == sudokuValues[position].toString()) {
            correctCells++
            correctCell = true
        }

        return correctCell
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var cellValue: TextView = v.cellValue
        var cellRoot: ConstraintLayout = v.cellRoot

        var cellBorderTop: View = v.cellBorderTop
        var cellBorderBottom: View = v.cellBorderBottom
        var cellBorderStart: View = v.cellBorderStart
        var cellBorderEnd: View = v.cellBorderEnd

    }

}