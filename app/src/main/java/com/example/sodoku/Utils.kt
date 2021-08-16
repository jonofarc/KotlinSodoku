package com.example.sodoku

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.ContentFrameLayout
import android.content.SharedPreferences


object Utils {


    private const val SPINNER_REMOVAL_MAX_TRIES = 50
    private val PREFS_NAME = "sudoku_preferences"

    var sharedPreferences: SharedPreferences? = null
    val backGroundColorPrefString ="backGroundColor"


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
                    root.postDelayed(Runnable { removeSpinner(root) }, 500)
                }
            }
        }))
    }

    private fun removeSpinner(root: ViewGroup, tries: Int = 0) {
        var tries = tries
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

    fun initSharedPreferences(context: Context){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getPreferenceValue(preferenceSting: String): String {

        return sharedPreferences?.getString(preferenceSting, "") ?: ""
    }

    fun setPreferenceValue(preferenceString: String, preferenceValue: String) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString(preferenceString, preferenceValue)
        editor?.apply()
    }


}