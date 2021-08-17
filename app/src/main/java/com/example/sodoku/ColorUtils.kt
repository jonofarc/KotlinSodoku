package com.example.sodoku

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log

object ColorUtils {

    val validColorRegEx = "^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})".toRegex()

    const val backGroundColorPrefString ="backGroundColor"
    const val textColorPrefString ="textColor"
    const val cellBackGroundColorPrefString ="cellBackGround"
    const val pertinentCellBackGroundColorPrefString ="pertinentCellBackGround"


    var backGroundColor = Color.parseColor("#000000")
    var textColor = Color.parseColor("#FFFFFF")
    var cellBackGroundColor = Color.parseColor("#000000")
    var pertinentCellBackGroundColor = Color.parseColor("#CDCDCD")

    fun updateColors() {

        val savedBackgroundColor = SharedPreferencesUtils.getPreferenceValue(backGroundColorPrefString)
        val savedTextColor = SharedPreferencesUtils.getPreferenceValue(textColorPrefString)
        val savedCellBackGroundColor = SharedPreferencesUtils.getPreferenceValue(cellBackGroundColorPrefString)

        //backGround Color
        if(savedBackgroundColor.matches(validColorRegEx)){
            try {
                val savedColor = Color.parseColor(savedBackgroundColor)
                backGroundColor = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting background color: $e")
            }

        }

        // Text Color
        if(savedTextColor.matches(validColorRegEx)){
            try {
                val savedColor = Color.parseColor(savedTextColor)
                textColor = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting text color: $e")
            }

        }

        // cell BackGround  Color
        if(savedCellBackGroundColor.matches(validColorRegEx)){
            try {
                val savedColor = Color.parseColor(savedCellBackGroundColor)
                cellBackGroundColor = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting cell background color: $e")
            }

        }

    }

}