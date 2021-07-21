package com.example.sodoku

import android.app.Activity
import android.content.Intent


interface MainActivityRepository {
    fun setDificultyLvl(lvl: Int)
    fun startGame()

}


class MainActivityImpl(private val activity: Activity) : MainActivityRepository {


    var difficultyLvl = 1

    override fun setDificultyLvl(lvl: Int) {
        difficultyLvl = lvl
    }


    override fun startGame() {
        val intent = Intent(activity, GameScreenActivity::class.java)
        intent.putExtra(GameScreenActivity.GAME_DIFICULTY, difficultyLvl)
        activity.startActivity(intent)
    }

}