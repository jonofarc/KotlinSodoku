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

class GameScreenActivity : AppCompatActivity() {
    private val activity:Activity = this@GameScreenActivity

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

        val gameScreenRepository = GameScreenImpl(activity)
        gameScreenRepository.setLvl()

        gameScreenRepository.setUI(sudokuRV, debugCorrectCells, gameCompletedCl, valuesLl1, valuesLl2)

        selectedValue1.setOnClickListener {
            gameScreenRepository.setCurrentValue(1)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue2.setOnClickListener {
            gameScreenRepository.setCurrentValue(2)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue3.setOnClickListener {
            gameScreenRepository.setCurrentValue(3)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue4.setOnClickListener {
            gameScreenRepository.setCurrentValue(4)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue5.setOnClickListener {
            gameScreenRepository.setCurrentValue(5)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue6.setOnClickListener {
            gameScreenRepository.setCurrentValue(6)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue7.setOnClickListener {
            gameScreenRepository.setCurrentValue(7)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue8.setOnClickListener {
            gameScreenRepository.setCurrentValue(8)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue9.setOnClickListener {
            gameScreenRepository.setCurrentValue(9)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }
        selectedValue10.setOnClickListener {
            gameScreenRepository.setCurrentValue(-1)
            gameScreenRepository.setValuesColor(valuesLl1,ContextCompat.getColorStateList(activity, R.color.gray))
            gameScreenRepository.setValuesColor(valuesLl2,ContextCompat.getColorStateList(activity, R.color.gray))
            it.backgroundTintList = ContextCompat.getColorStateList(activity, R.color.teal_200)
        }

        finishBtn.setOnClickListener {
            finish()
        }

        gameScreenSettingsIv.setOnClickListener {
            settingsCL.visibility = View.VISIBLE
        }

        gameScreenSettingsIv2.setOnClickListener {
            settingsCL.visibility = View.GONE
        }

        backGroundColorBtn.setOnClickListener {
            Utils.setPreferenceValue(Utils.backGroundColorPrefString, backGroundColorEt.text.toString())
        }

    }

    private fun setColors() {

        val backgroundColor = Utils.getPreferenceValue(Utils.backGroundColorPrefString)
        if(backgroundColor.isNotEmpty()){
            try {
                val savedColor = ColorStateList.valueOf(Color.parseColor(backgroundColor))
                gameScreenCL.backgroundTintList = savedColor

            }catch (e: Error){
                Log.d(GameScreenImpl.TAG, "problem getting background color: $e")
            }

        }


    }

    override fun onResume() {
        super.onResume()
        setColors()
    }


}