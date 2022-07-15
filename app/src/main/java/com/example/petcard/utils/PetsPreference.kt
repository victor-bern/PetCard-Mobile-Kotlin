package com.example.petcard.utils

import android.content.Context

class PetsPreference(val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("userPreference", Context.MODE_PRIVATE)

    fun store(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getValue(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun clear() {
        sharedPreferences.edit().clear().commit()
    }


}