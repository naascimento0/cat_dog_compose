package com.example.catdogincompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * Provides access to a [DataStore] instance for storing user preferences.
 *
 * This property utilizes the [preferencesDataStore] delegate to create and manage
 * a [DataStore] that uses the Preferences implementation for storing key-value pairs.
 *
 * The [DataStore] is named "UserPrefs" and will store data in a file with that name
 * within the application's data directory.
 *
 * **Key Features:**
 *
 * - **Convenient Access:** Provides a simple way to access the DataStore from any Context.
 * - **Preferences Implementation:** Uses the Preferences implementation for storing simple data types.
 * - **Singleton Instance:** Ensures a single DataStore instance is shared across the application.
 * - **Lifecycle Aware:** Handles DataStore creation and management automatically.
 * - **Data Persistence:** Data is persisted across application restarts and device reboots.
 *
 * **Usage:**
 *
 * ```kotlin
 * // To access the DataStore:
 * val dataStore = context.dataStore
 *
 * // To write data to the DataStore (example):
 * lifecycleScope.launch {
 *     context.dataStore.edit { preferences ->
 *         preferences[stringPreferencesKey("userName")] = "John Doe"
 *     }
 * }
 *
 * // To read data from the DataStore (example):
 * lifecycleScope.launch {
 *     context.dataStore.data.collect { preferences ->
 *         val userName = preferences[stringPreferencesKey("userName")]
 *         // ... use userName ...
 *     }
 * }
 * ```
 *
 * **Note:**
 *
 * - You'll need to include the `androidx.datastore:datastore-preferences` dependency in your `build.gradle` file.
 * - You need to use keys created by the `preferences` extension functions e.g. `stringPreferencesKey`, `intPreferencesKey`.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "UserPrefs")

class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("userName")
    }

    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    fun getUserName(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_NAME_KEY]
        }
    }
}