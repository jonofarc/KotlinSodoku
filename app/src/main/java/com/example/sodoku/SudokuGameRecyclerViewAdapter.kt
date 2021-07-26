package com.example.sodoku

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_layout.view.*


class SudokuGameRecyclerViewAdapter(
    private val sudokuValues: MutableList<Int>,
    val width: Int,
    val height: Int,
    val cellClick: (Any) -> Unit
) : RecyclerView.Adapter<SudokuGameRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_layout, parent, false))
    }

    override fun getItemCount(): Int = sudokuValues.size

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val params = holder.cellRoot.layoutParams

        params.height = height/9
        params.width = width/9
        holder.cellRoot.layoutParams = params



        holder.cellValue.text = sudokuValues[position].toString()
        holder.cellRoot.setOnClickListener{

            cellClick(position)

        }

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var cellValue: TextView = v.cellValue
        var cellRoot: ConstraintLayout = v.cellRoot


    }

}