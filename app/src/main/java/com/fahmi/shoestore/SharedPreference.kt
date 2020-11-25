package com.fahmi.shoestore

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val PREFS_NAME = "Shoe Store"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPref.edit()

    fun save(KEY_NAME: String, text: String) {
        editor.putString(KEY_NAME, text)
    }

    fun save(KEY_NAME: String, value: Int) {
        editor.putInt(KEY_NAME, value)
    }

    fun save(KEY_NAME: String, status: Boolean) {
        editor.putBoolean(KEY_NAME, status)
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, "")
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }

    fun getValueBoolien(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }

    fun clearSharedPreference() {
        editor.clear()
    }

    fun removeValue(KEY_NAME: String) {
        editor.remove(KEY_NAME)
    }
    fun submit(){
        editor.apply()
    }
}
