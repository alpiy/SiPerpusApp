package com.example.siperpus.config

import android.content.Context
import android.content.SharedPreferences

class SharedPrafManager(context: Context) {
    private val PREFS_NAME = "sharedpref1234"
    private val sharedPref: SharedPreferences
    val editor:SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor =sharedPref.edit()
    }

    fun put(key: String, value: Any) {
        when(value) {
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
        }
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key,false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key,null)
    }

    fun clear() {
        editor.clear().apply()
    }
}