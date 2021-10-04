package com.example.sodoku

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView


class GameScreenActivity : AppCompatActivity() {
    private val activity: Activity = this@GameScreenActivity
    lateinit var gameScreenRepository: GameScreenImpl

    companion object {
        val GAME_DIFFICULTY =
            GameScreenActivity::class.java.simpleName + "GAME_DIFFICULTY"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        Utils.setSpinner(this@GameScreenActivity, true)
        lifecycleScope.launch(Dispatchers.Main) {

            setUI()
            Utils.setSpinner(this@GameScreenActivity, false)
        }

    }

    private fun setUI() {

        gameScreenRepository = GameScreenImpl(activity)
        gameScreenRepository.setLvl()

        gameScreenRepository.setUI(
            sudokuRV,
            debugCorrectCells,
            gameCompletedCl
        )

        selectedValue1.setOnClickListener {
            gameScreenRepository.setCurrentValue(1)
            updateSelectedValues()
        }
        selectedValue2.setOnClickListener {
            gameScreenRepository.setCurrentValue(2)
            updateSelectedValues()
        }
        selectedValue3.setOnClickListener {
            gameScreenRepository.setCurrentValue(3)
            updateSelectedValues()
        }
        selectedValue4.setOnClickListener {
            gameScreenRepository.setCurrentValue(4)
            updateSelectedValues()
        }
        selectedValue5.setOnClickListener {
            gameScreenRepository.setCurrentValue(5)
            updateSelectedValues()
        }
        selectedValue6.setOnClickListener {
            gameScreenRepository.setCurrentValue(6)
            updateSelectedValues()
        }
        selectedValue7.setOnClickListener {
            gameScreenRepository.setCurrentValue(7)
            updateSelectedValues()
        }
        selectedValue8.setOnClickListener {
            gameScreenRepository.setCurrentValue(8)
            updateSelectedValues()
        }
        selectedValue9.setOnClickListener {
            gameScreenRepository.setCurrentValue(9)
            updateSelectedValues()
        }

        deleteSelectedValue.setOnClickListener {
            gameScreenRepository.setCurrentValue(-1)
            updateSelectedValues()
        }

        finishBtn.setOnClickListener {
            finish()
        }

        gameScreenSettingsIv.setOnClickListener {
            Utils.openAnimated(settingsCL)
        }

        gameScreenSettingsIv2.setOnClickListener {
            Utils.closeAnimated(settingsCL)
            settingsCL.visibility = View.GONE
        }

        //set saved colors
        setColors()

        //color settings buttons
        backGroundColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.backGroundColorPrefString,
                backGroundColorEt.text.toString()
            )
            setColors()
        }
        backGroundColorEt.setText(ColorUtils.savedBackgroundColor)

        textColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.textColorPrefString,
                textColorEt.text.toString()
            )
            setColors()
        }
        textColorEt.setText(ColorUtils.savedTextColor)

        cellBackGroundColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.cellBackGroundColorPrefString,
                cellBackGroundColorEt.text.toString()
            )
            setColors()
        }
        cellBackGroundColorEt.setText(ColorUtils.savedCellBackGroundColor)

        pertinentCellBackGroundColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.pertinentCellBackGroundColorPrefString,
                pertinentCellBackGroundColorEt.text.toString()
            )
            setColors()
        }
        pertinentCellBackGroundColorEt.setText(ColorUtils.savedPertinentCellBackGroundColor)


        buttonsColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.buttonColorPrefString,
                buttonsColorEt.text.toString()
            )
            setColors()
        }
        buttonsColorEt.setText(ColorUtils.savedButtonColor)

        buttonsTextColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.buttonTextColorPrefString,
                buttonsTextColorEt.text.toString()
            )
            setColors()
        }
        buttonsTextColorEt.setText(ColorUtils.savedButtonTextColor)

        selectedValuesTextColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.selectedValuesColorPrefString,
                selectedValuesTextColorEt.text.toString()
            )
            setColors()
        }
        selectedValuesTextColorEt.setText(ColorUtils.savedSelectedValuesColor)

        userInputsTextColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.userInputsTextColorPrefString,
                userInputsTextColorEt.text.toString()
            )
            setColors()
        }
        userInputsTextColorEt.setText(ColorUtils.savedUserInputsTextColor)

        userInputsCorrectTextColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.userInputsCorrectTextColorPrefString,
                userInputsCorrectTextColorEt.text.toString()
            )
            setColors()
        }
        userInputsCorrectTextColorEt.setText(ColorUtils.savedUserInputsCorrectTextColor)

        sudokuBorderColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.sudokuBorderColorPrefString,
                sudokuBorderColorEt.text.toString()
            )
            setColors()
        }
        sudokuBorderColorEt.setText(ColorUtils.savedSudokuBorderColor)

        cellBorderColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(
                ColorUtils.cellBorderColorPrefString,
                cellBorderColorEt.text.toString()
            )
            setColors()
        }
        cellBorderColorEt.setText(ColorUtils.savedCellBorderColor)

        updateSelectedValues()

    }

    //hide or show values button depending if there are more options for them or not (ex: the user already set all the numbers 1 available)
    private fun updateSelectedValues() {
        selectedValue1.visibility = View.VISIBLE
        selectedValue2.visibility = View.VISIBLE
        selectedValue3.visibility = View.VISIBLE
        selectedValue4.visibility = View.VISIBLE
        selectedValue5.visibility = View.VISIBLE
        selectedValue6.visibility = View.VISIBLE
        selectedValue7.visibility = View.VISIBLE
        selectedValue8.visibility = View.VISIBLE
        selectedValue9.visibility = View.VISIBLE

        gameScreenRepository.updateSelectedValues()
        gameScreenRepository.answersHideList.forEach {

            when(it){
                1 -> selectedValue1.visibility = View.INVISIBLE
                2 -> selectedValue2.visibility = View.INVISIBLE
                3 -> selectedValue3.visibility = View.INVISIBLE
                4 -> selectedValue4.visibility = View.INVISIBLE
                5 -> selectedValue5.visibility = View.INVISIBLE
                6 -> selectedValue6.visibility = View.INVISIBLE
                7 -> selectedValue7.visibility = View.INVISIBLE
                8 -> selectedValue8.visibility = View.INVISIBLE
                9 -> selectedValue9.visibility = View.INVISIBLE
            }
        }
    }

    private fun setColors() {
        //update colors if they exist from shared preferences
        ColorUtils.updateColors()

        //apply colors

        //backGround
        gameScreenCL.setBackgroundColor(ColorUtils.backGroundColor)
        backGroundColorPreviewV.backgroundTintList =
            ColorStateList.valueOf(ColorUtils.backGroundColor)

        //sudoku Text
        textColorPreviewV.setBackgroundColor(ColorUtils.textColor)

        //cell background
        cellBackGroundColorPreviewV.setBackgroundColor(ColorUtils.cellBackGroundColor)

        //pertinent cell background
        pertinentCellBackGroundColorPreviewV.setBackgroundColor(ColorUtils.pertinentCellBackGroundColor)

        //selected value text cell
        selectedValuesTextColorPreviewV.setBackgroundColor(ColorUtils.selectedValuesColor)

        //user input text color
        userInputsTextColorPreviewV.setBackgroundColor(ColorUtils.userInputsTextColor)

        //user input correct text color
        userInputsCorrectTextColorPreviewV.setBackgroundColor(ColorUtils.userInputsCorrectTextColor)

        //sudoku border
        sudokuBorderColorPreviewV.setBackgroundColor(ColorUtils.sudokuBorderColor)

        //sudoku border
        cellBorderColorPreviewV.setBackgroundColor(ColorUtils.cellBorderColor)

        //update elements inside sudoku Matrix
        gameScreenRepository.notifyDataSetChanged()

        //button color
        valuesLl1.children.forEach {
            if(it is Button){
                it.setBackgroundColor(ColorUtils.buttonsColor)
            }
        }
        valuesLl2.children.forEach {
            if(it is Button){
                it.setBackgroundColor(ColorUtils.buttonsColor)
            }
        }
        //deleteSelectedValue.setBackgroundColor(ColorUtils.buttonsColor)
        buttonsColorPreviewV.setBackgroundColor(ColorUtils.buttonsColor)


        //button text color
        valuesLl1.children.forEach {
            if(it is Button){
                it.setTextColor(ColorUtils.buttonsTextColor)
            }
        }
        valuesLl2.children.forEach {
            if(it is Button){
                it.setTextColor(ColorUtils.buttonsTextColor)
            }
        }
        deleteSelectedValue.imageTintList = ColorStateList.valueOf(ColorUtils.buttonsTextColor)



        buttonsTextColorPreviewV.setBackgroundColor(ColorUtils.buttonsTextColor)


    }

}