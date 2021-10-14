package com.example.sodoku

import android.content.Context
import com.example.sudoku.Utils
import java.util.*

/**
 * Convenience extension method for getting pixels from dp
 */
fun Int.toPixel(context: Context): Int {
    return Utils.convertDpToPixel(context, this.toFloat()).toInt()
}
