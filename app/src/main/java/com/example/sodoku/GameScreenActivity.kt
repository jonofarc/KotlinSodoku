package com.example.sodoku

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GameScreenActivity : AppCompatActivity() {
    private val activity:Activity = this@GameScreenActivity
    lateinit var gameScreenRepository : GameScreenImpl

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

        gameScreenRepository.setUI(sudokuRV, debugCorrectCells, gameCompletedCl, valuesLl1, valuesLl2)

        selectedValue1.setOnClickListener {
            gameScreenRepository.setCurrentValue(1)
        }
        selectedValue2.setOnClickListener {
            gameScreenRepository.setCurrentValue(2)
        }
        selectedValue3.setOnClickListener {
            gameScreenRepository.setCurrentValue(3)
        }
        selectedValue4.setOnClickListener {
            gameScreenRepository.setCurrentValue(4)
        }
        selectedValue5.setOnClickListener {
            gameScreenRepository.setCurrentValue(5)
        }
        selectedValue6.setOnClickListener {
            gameScreenRepository.setCurrentValue(6)
        }
        selectedValue7.setOnClickListener {
            gameScreenRepository.setCurrentValue(7)
        }
        selectedValue8.setOnClickListener {
            gameScreenRepository.setCurrentValue(8)
        }
        selectedValue9.setOnClickListener {
            gameScreenRepository.setCurrentValue(9)
        }
        selectedValue10.setOnClickListener {
            gameScreenRepository.setCurrentValue(-1)
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

        backGroundColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(ColorUtils.backGroundColorPrefString, backGroundColorEt.text.toString())
            setColors()
        }

        textColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(ColorUtils.textColorPrefString, textColorEt.text.toString())
            setColors()
        }

        cellBackGroundColorBtn.setOnClickListener {
            SharedPreferencesUtils.setColorPreferenceValue(ColorUtils.cellBackGroundColorPrefString, cellBackGroundColorEt.text.toString())
            setColors()
        }


        //set saved colors
        setColors()

    }

    private fun setColors() {
        //update colors if they exist from shared preferences
        ColorUtils.updateColors()

        //apply colors

        //backGround
        gameScreenCL.setBackgroundColor(ColorUtils.backGroundColor)
        backGroundColorPreviewV.backgroundTintList = ColorStateList.valueOf(ColorUtils.backGroundColor)

        //sudoku Text
        textColorPreviewV.setBackgroundColor(ColorUtils.textColor)

        //cell background
        cellBackGroundColorPreviewV.setBackgroundColor(ColorUtils.cellBackGroundColor)


        //update elements inside sudoku Matrix
        gameScreenRepository.notifyDataSetChanged()

    }

}