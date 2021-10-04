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
    const val userInputsCorrectTextColorPrefString = "userInputsCorrectTextColorPrefString"
    const val sudokuBorderColorPrefString = "SudokuBorderColorPrefString"
    const val cellBorderColorPrefString = "cellBorderColorPrefString"


    var backGroundColor = Color.parseColor("#000000")
    var textColor = Color.parseColor("#FFFFFF")
    var cellBackGroundColor = Color.parseColor("#000000")
    var pertinentCellBackGroundColor = Color.parseColor("#CDCDCD")
    var buttonsColor = Color.parseColor("#CDCDCD")
    var buttonsTextColor = Color.parseColor("#000000")
    var selectedValuesColor = Color.parseColor("#2694A6")
    var userInputsTextColor = Color.parseColor("#D6050D")
    var userInputsCorrectTextColor = Color.parseColor("#0DC143")
    var sudokuBorderColor = Color.parseColor("#FFBB86FC")
    var cellBorderColor = Color.parseColor("#FFFFFF")


    var savedBackgroundColor = ""
    var savedTextColor = ""
    var savedCellBackGroundColor = ""
    var savedPertinentCellBackGroundColor = ""
    var savedButtonColor = ""
    var savedButtonTextColor = ""
    var savedSelectedValuesColor = ""
    var savedUserInputsTextColor = ""
    var savedUserInputsCorrectTextColor = ""
    var savedSudokuBorderColor = ""
    var savedCellBorderColor = ""

    fun updateColors() {

        savedBackgroundColor = SharedPreferencesUtils.getPreferenceValue(backGroundColorPrefString)
        savedTextColor = SharedPreferencesUtils.getPreferenceValue(textColorPrefString)
        savedCellBackGroundColor = SharedPreferencesUtils.getPreferenceValue(cellBackGroundColorPrefString)
        savedPertinentCellBackGroundColor = SharedPreferencesUtils.getPreferenceValue(pertinentCellBackGroundColorPrefString)
        savedButtonColor = SharedPreferencesUtils.getPreferenceValue(buttonColorPrefString)
        savedButtonTextColor = SharedPreferencesUtils.getPreferenceValue(buttonTextColorPrefString)
        savedSelectedValuesColor = SharedPreferencesUtils.getPreferenceValue(selectedValuesColorPrefString)
        savedUserInputsTextColor = SharedPreferencesUtils.getPreferenceValue(userInputsTextColorPrefString)
        savedUserInputsCorrectTextColor = SharedPreferencesUtils.getPreferenceValue(userInputsCorrectTextColorPrefString)
        savedSudokuBorderColor = SharedPreferencesUtils.getPreferenceValue(sudokuBorderColorPrefString)
        savedCellBorderColor = SharedPreferencesUtils.getPreferenceValue(cellBorderColorPrefString)

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
                Log.d(GameScreenImpl.TAG, "problem getting user inputs text color: $e")
            }

        }

        // user inputs text values color
        if (savedUserInputsCorrectTextColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedUserInputsCorrectTextColor)
                userInputsCorrectTextColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting user inputs text color: $e")
            }

        }

        // sudoku border color
        if (savedSudokuBorderColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedSudokuBorderColor)
                sudokuBorderColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting sudoku border color: $e")
            }

        }

        // cell border color
        if (savedCellBorderColor.matches(validColorRegEx)) {
            try {
                val savedColor = Color.parseColor(savedCellBorderColor)
                cellBorderColor = savedColor

            } catch (e: Error) {
                Log.d(GameScreenImpl.TAG, "problem getting cell border color: $e")
            }

        }

    }

}