package com.example.catdogincompose.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Repository for user preferences.
 *
 * This repository is used to manage the user preferences.
 */
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // UserPrefs is the name of the file that will store the user preferences, Context.MODE_PRIVATE is the mode that indicates that the file can only be accessed by the app
    private val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun getUserName(): String {
        return sharedPreferences.getString("userName", "") ?: ""
    }

    fun saveUserName(name: String) {
        sharedPreferences.edit().putString("userName", name).apply()
    }
}