package com.jonofarc.sudoku

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.widget.Toast

object SharedPreferencesUtils {

    private val TAG: String = SharedPreferencesUtils::class.java.simpleName+"_TAG"
    private const val PREFS_NAME = "sudoku_preferences"

    fun initSharedPreferences(context: Context){
        Utils.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getPreferenceValue(preferenceSting: String): String {

        return Utils.sharedPreferences?.getString(preferenceSting, "") ?: ""
    }

    fun setPreferenceValue(preferenceString: String, preferenceValue: String) {
        val editor: SharedPreferences.Editor? = Utils.sharedPreferences?.edit()
        editor?.putString(preferenceString, preferenceValue)
        editor?.apply()
    }

    fun setColorPreferenceValue(preferenceString: String, preferenceValue: String) {
        var valueString = preferenceValue
        if(!valueString.startsWith("#")){
            valueString = "#$valueString"
        }
        val validColor = valueString.matches(ColorUtils.validColorRegEx)
        if(validColor){
            val editor: SharedPreferences.Editor? = Utils.sharedPreferences?.edit()
            editor?.putString(preferenceString, valueString)
            editor?.apply()
        }else{
            Log.e(TAG, "error saved color for background is not valid")
        }

    }

}