package com.jonofarc.sodoku

import android.content.Context
import com.jonofarc.sudoku.Utils

/**
 * Convenience extension method for getting pixels from dp
 */
fun Int.toPixel(context: Context): Int {
    return Utils.convertDpToPixel(context, this.toFloat()).toInt()
}
