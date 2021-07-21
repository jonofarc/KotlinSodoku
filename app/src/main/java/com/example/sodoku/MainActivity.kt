package com.example.sodoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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


        dificultySb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                dificultyValue.text = progress.toString()
                mainActivityRepository.setDificultyLvl(progress)
            }
        })

        startGameBtn.setOnClickListener {
            mainActivityRepository.startGame()
        }



    }
}