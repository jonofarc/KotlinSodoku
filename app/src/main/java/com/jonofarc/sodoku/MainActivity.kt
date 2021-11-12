package com.jonofarc.sudoku

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val easyValue = 35
    private val normalValue = 46
    private val hardValue = 51

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUI()
    }

    private fun setUI() {

        val mainActivityRepository = MainActivityImpl(this@MainActivity)

        dificultyValue.text = "1"
        dificultySb.max = 81
        dificultySb.min = 1

        SharedPreferencesUtils.initSharedPreferences(this@MainActivity)

        dificultySb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                dificultyValue.text = progress.toString()
                mainActivityRepository.setDifficultyLvl(progress)

                when {
                    progress > hardValue -> {
                        dificultyValue.setTextColor(resources.getColor(R.color.red))
                    }
                    progress > normalValue -> {
                        dificultyValue.setTextColor(resources.getColor(R.color.yellow))
                    }
                    else -> {
                        dificultyValue.setTextColor(resources.getColor(R.color.green))
                    }
                }
            }
        })

        startGameBtn.setOnClickListener {
            mainActivityRepository.startGame()
        }

        easyLevelGameBtn.setOnClickListener {
            mainActivityRepository.setDifficultyLvl(easyValue)
            mainActivityRepository.startGame()
        }

        normalLevelGameBtn.setOnClickListener {
            mainActivityRepository.setDifficultyLvl(normalValue)
            mainActivityRepository.startGame()
        }

        hardLevelGameBtn.setOnClickListener {
            mainActivityRepository.setDifficultyLvl(hardValue)
            mainActivityRepository.startGame()
        }


    }
}