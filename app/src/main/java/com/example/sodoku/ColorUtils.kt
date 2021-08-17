package com.example.sodoku

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log

object ColorUtils {

    val validColorRegEx = "^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})".toRegex()

    const val backGroundColorPrefString = "backGroundColor"
    const val textColorPrefString = "textColor"
    const val cellBackGroundColorPrefString = "cellBackGround"
    const val pertinentCellBackGroundColorPrefString = "pertinentCellBackGround"
    const val buttonColorPrefString = "ButtonColorPrefString"
    const val buttonTextColorPrefString = "ButtonTextColorPrefString"
    const val selectedValuesColorPrefString = "SelectedValuesColorPrefString"
    const val userInputsTextColorPrefString = "UserInputsTextColorPrefString"


    var backGroundColor = Color.parseColor("#000000")
    var textColor = Color.parseColor("#FFFFFF")
    var cellBackGroundColor = Color.parseColor("#000000")
    var pertinentCellBackGroundColor = Color.parseColor("#CDCDCD")
    var buttonsColor = Color.parseColor("#CDCDCD")
    var buttonsTextColor = Color.parseColor("#000000")
    var selectedValuesColor = Color.parseColor("#2694A6")
    var userInputsTextColor = Color.parseColor("#D6050D")

    fun updateColors() {

        val savedBackgroundColor = SharedPreferencesUtils.getPreferenceValue(backGroundColorPrefString)
        val savedTextColor = SharedPreferencesUtils.getPreferenceValue(textColorPrefString)
        val savedCellBackGroundColor = SharedPreferencesUtils.getPreferenceValue(cellBackGroundColorPrefString)
        val savedPertinentCellBackGroundColor = SharedPreferencesUtils.getPreferenceValue(pertinentCellBackGroundColorPrefString)
        val savedButtonColor = SharedPreferencesUtils.getPreferenceValue(buttonColorPrefString)
        val savedButtonTextColor = SharedPreferencesUtils.getPreferenceValue(buttonTextColorPrefString)
        val savedSelectedValuesColor = SharedPreferencesUtils.getPreferenceValue(selectedValuesColorPrefString)
        val savedUserInputsTextColor = SharedPreferencesUtils.getPreferenceValue(userInputsTextColorPrefString)

        //backGround Color
        if (savedBackgroundColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedBackgroundColor)
                backGroundColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting background color: $e")
            }

        }

        // Text Color
        if (savedTextColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedTextColor)
                textColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting text color: $e")
            }

        }

        // cell BackGround  Color
        if (savedCellBackGroundColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedCellBackGroundColor)
                cellBackGroundColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting cell background color: $e")
            }

        }

        // pertinent cell BackGround  Color
        if (savedPertinentCellBackGroundColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedPertinentCellBackGroundColor)
                pertinentCellBackGroundColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting pertinent cell background color: $e")
            }

        }

        // buttons Color
        if (savedButtonColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedButtonColor)
                buttonsColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting button color: $e")
            }

        }

        // buttons text Color
        if (savedButtonTextColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedButtonTextColor)
                buttonsTextColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting text color: $e")
            }

        }

        // selected values color
        if (savedSelectedValuesColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedSelectedValuesColor)
                selectedValuesColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting selected value text color: $e")
            }

        }

        // user inputs text values color
        if (savedUserInputsTextColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedUserInputsTextColor)
                userInputsTextColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting selected value text color: $e")
            }

        }

    }

}