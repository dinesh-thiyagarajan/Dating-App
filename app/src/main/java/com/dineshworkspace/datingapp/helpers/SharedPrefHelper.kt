package com.dineshworkspace.datingapp.helpers

import android.content.Context
import android.content.SharedPreferences
import com.dineshworkspace.datingapp.DatingApp

object SharedPrefHelper {

    private var sharedPreferences: SharedPreferences = DatingApp.application.getSharedPreferences(
        AppConstants.SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun saveBoolean(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String?, defaultVale: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultVale)
    }

    fun saveString(key: String?, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String?, defaultVale: String): String? {
        return sharedPreferences.getString(key, defaultVale)
    }
}