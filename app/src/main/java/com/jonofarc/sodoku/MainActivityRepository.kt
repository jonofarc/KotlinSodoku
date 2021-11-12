package com.jonofarc.sudoku

import android.app.Activity
import android.content.Intent


interface MainActivityRepository {
    fun setDifficultyLvl(lvl: Int)
    fun startGame()

}


class MainActivityImpl(private val activity: Activity) : MainActivityRepository {


    var dLvl = 1

    override fun setDifficultyLvl(lvl: Int) {
        dLvl = lvl
    }


    override fun startGame() {
        val intent = Intent(activity, GameScreenActivity::class.java)
        intent.putExtra(GameScreenActivity.GAME_DIFFICULTY, dLvl)
        activity.startActivity(intent)
    }

}