package com.example.sodoku

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log

object ColorUtils {

    val validColorRegEx = "^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})".toRegex()

    val backGroundColorPrefString ="backGroundColor"
    val textColorPrefString ="textColor"


    var backGroundColor = ColorStateList.valueOf(Color.parseColor("#000000"))
    var textColor = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))

    fun updateColors() {

        val savedBackgroundColor = SharedPreferencesUtils.getPreferenceValue(backGroundColorPrefString)
        val savedTextColor = SharedPreferencesUtils.getPreferenceValue(textColorPrefString)

        //backGround Color
        if(savedBackgroundColor.matches(validColorRegEx)){
            try {
                val savedColor = ColorStateList.valueOf(Color.parseColor(savedBackgroundColor))
                backGroundColor = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting background color: $e")
            }

        }

        // Text Color
        if(savedTextColor.matches(validColorRegEx)){
            try {
                val savedColor = ColorStateList.valueOf(Color.parseColor(savedTextColor))
                textColor = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting background color: $e")
            }

        }

    }

}