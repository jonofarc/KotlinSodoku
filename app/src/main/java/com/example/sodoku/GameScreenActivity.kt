package com.example.sodoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class GameScreenActivity : AppCompatActivity() {


    companion object {
        val GAME_DIFICULTY =
            GameScreenActivity::class.java.simpleName + "_GAME_DIFICULTY"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        setUI()
    }

    private fun setUI() {
        val gameScreenRepository = GameScreenImpl(this@GameScreenActivity)
        gameScreenRepository.setLvl()

    }


}