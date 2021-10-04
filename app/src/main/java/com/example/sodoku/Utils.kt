package com.example.sudoku

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.ContentFrameLayout
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import kotlinx.android.synthetic.main.activity_game_screen.*
import android.view.animation.TranslateAnimation





object Utils {


    private const val SPINNER_REMOVAL_MAX_TRIES = 50

    var sharedPreferences: SharedPreferences? = null


    @JvmStatic
    //function to add spiner to whole screen
    fun setSpinner(activity: Activity, spin: Boolean) {
        val root: ContentFrameLayout = activity.findViewById(android.R.id.content)
        setSpinner(activity, root, spin)
    }

    //function to add a spinner to a specific view
    @JvmStatic
    fun setSpinner(activity: Activity, viewAttachment: View?, spin: Boolean) {
        activity.runOnUiThread(Thread(Runnable {
            val root = viewAttachment as ViewGroup?
            val spinner: RelativeLayout
            if (root != null) {
                if (root.findViewById<View?>(R.id.generic_spinner) == null) {
                    if (spin) {
                        val view = LayoutInflater.from(activity).inflate(R.layout.spinner, root)
                    }
                } else { //spinner has already been added
                    spinner = root.findViewById(R.id.generic_spinner)
                    spinner.visibility = View.GONE
                    root.removeView(spinner)
                    val view = LayoutInflater.from(activity).inflate(R.layout.spinner, root)
                }
                if (!spin) {
                    root.postDelayed(Runnable { removeSpinner(root) }, 2000)
                }
            }
        }))
    }

    private fun removeSpinner(root: ViewGroup, recivingTries: Int = 0) {
        var tries = recivingTries
        var spinner = root.findViewById<RelativeLayout>(R.id.generic_spinner)
        tries++
        if (spinner != null) {
            spinner.visibility = View.GONE
            (spinner.parent as ViewGroup).removeView(spinner)
        }
        spinner = root.findViewById(R.id.generic_spinner)
        if (spinner != null && tries < SPINNER_REMOVAL_MAX_TRIES) {
            removeSpinner(root, tries)
        }
    }

    fun openAnimated(view: View){
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(0F, 0F, view.height.toFloat(), 0F )
        animate.duration = 500
        view.startAnimation(animate)
    }

    fun closeAnimated(view: View){
        view.visibility = View.GONE
        val animate = TranslateAnimation(0F, 0F, 0F, view.height.toFloat())
        animate.duration = 500
        view.startAnimation(animate)

    }





}