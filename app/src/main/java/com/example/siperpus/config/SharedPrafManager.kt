package com.example.siperpus.config

import android.content.Context
import android.content.SharedPreferences

object SharedPrefManager {
    private const val PREF_NAME = "user_session"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, MODE)
    }

    fun put(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}