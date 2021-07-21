package com.example.sodoku

import android.app.Activity
import android.widget.Toast


interface GameScreenRepository {

    fun setLvl()
}

class GameScreenImpl(private val activity: Activity) : GameScreenRepository {
    private var difficultyLvl = 1

    override fun setLvl() {

        val dificultyLvlExtra = activity.intent.extras?.getInt(GameScreenActivity.GAME_DIFICULTY, 1) ?: 1
        difficultyLvl = dificultyLvlExtra
        Toast.makeText(activity,"Difficulty set to $difficultyLvl", Toast.LENGTH_SHORT).show()
    }
}
