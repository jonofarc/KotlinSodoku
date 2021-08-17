package com.example.sodoku

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log

object ColorUtils {

    val validColorRegEx = "^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})".toRegex()

    val backGroundColorPrefString ="backGroundColor"


    var backGroundColor = ColorStateList.valueOf(Color.parseColor("#000000"))

    fun updateColors() {

        val backgroundColor = SharedPreferencesUtils.getPreferenceValue(backGroundColorPrefString)
        if(backgroundColor.matches(validColorRegEx)){
            try {
                val savedColor = ColorStateList.valueOf(Color.parseColor(backgroundColor))
                backGroundColor = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting background color: $e")
            }

        }

    }

}