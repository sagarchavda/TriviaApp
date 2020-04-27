package com.appscrip.triviaapp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Sagar Chavda on 25/02/19.
 * It's common class for perform any option like save, get or delete value from shared preference
 */
class SharedPrefHelper(context: Context) {
    private val sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_FILE = "TriviaApp"
        const val NAME = "name"
        const val ANS1 = "ans1"
        const val ANS2 = "ans2"
    }

    /**
     * It's use to save value in shared preference.
     */
    fun setPreference(key: String?, value: Any?) {
        val editor = sharedPreferences.edit()
        if (value is Int) editor.putInt(
            key,
            value
        ) else if (value is Long) editor.putLong(
            key,
            value
        ) else if (value is String) editor.putString(
            key,
            value
        ) else if (value is Boolean) editor.putBoolean(
            key,
            value
        ) else editor.putString(key, value.toString())
        editor.apply()
    }

    /**
     * It's delete particular value based on passed preference key
     */
    fun deletePreference(key: String?) {
        sharedPreferences.edit().remove(key).apply()
    }

    /**
     * It's delete all store preference data
     */
    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    /**
     * It's return long data type value from shared preference
     */
    fun getLong(key: String?, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    /**
     * It's return integer data type value from shared preference
     */
    fun getInt(key: String?, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    /**
     * It's return string data type value from shared preference
     */
    fun getString(key: String?, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    /**
     * It's return boolean data type value from shared preference
     */
    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    init {
        sharedPreferences = context.getSharedPreferences(
            PREFS_FILE,
            Context.MODE_PRIVATE
        )
    }
}