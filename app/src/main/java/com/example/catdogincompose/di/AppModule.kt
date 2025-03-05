package com.example.catdogincompose.di

import android.content.Context
import androidx.room.Room
import com.example.catdogincompose.data.local.dao.PhraseDao
import com.example.catdogincompose.data.local.database.AppDatabase
import com.example.catdogincompose.data.remote.api.PhraseApiService
import com.example.catdogincompose.data.repository.PhraseRepository
import com.example.catdogincompose.data.repository.UserPreferencesRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

/**
-- * Module that provides dependencies for the app.
-+ * **AppModule**
-+ *
-+ * This Dagger module provides application-wide dependencies. It configures and
-+ * provides singleton instances of core components like `PhraseRepository`,
-+ * `UserPreferencesRepository`, `Retrofit`, and `PhraseApiService`. These
-+ * dependencies are scoped to the `SingletonComponent`, meaning they are
-+ * created once and shared throughout the application's lifecycle.
+ * # AppModule
+ *
+ * **Overview**
+ *
+ * `AppModule` is a Dagger module responsible for providing application-wide dependencies. It defines how various
+ * core components of the application are constructed and managed.
+ *
+ * **Purpose**
+ *
+ * This module configures and provides singleton instances of essential components, ensuring they are created only once
+ * and shared throughout the application's lifecycle. These components include:
+ *
+ * - `PhraseRepository`: Manages access to phrases from both network and local database.
+ * - `UserPreferencesRepository`: Handles user preferences storage and retrieval.
+ * - `Retrofit`: Provides network communication capabilities.
+ * - `PhraseApiService`: Defines the API endpoints for interacting with the phrase service.
+ * - `AppDatabase`: Manages the local Room database.
+ * - `PhraseDao`: Defines the Data Access Object for phrase entities.
+ *
+ * **Scope**
+ *
+ * All dependencies provided by this module are scoped to `SingletonComponent`, guaranteeing their singleton nature
+ * across the entire application.
 */
@Module // anotação que indica que essa classe é um módulo do Dagger
@InstallIn(SingletonComponent::class) // anotação que indica que as dependências desse módulo são instâncias únicas
object AppModule {

    @Provides // anotação que indica que esse métodoo fornece uma dependência
    @Singleton // anotação que indica que a dependência é única e durará por toda a vida do app
    fun providePhraseRepository(phraseApiService: PhraseApiService, phraseDao: PhraseDao): PhraseRepository {
        return PhraseRepository(phraseApiService, phraseDao)
    }

    @Provides // anotação que indica que esse métodoo fornece uma dependência
    @Singleton // anotação que indica que a dependência é única e durará por toda a vida do app
    fun provideUserPreferencesRepository(@ApplicationContext context: Context): UserPreferencesRepository {
        return UserPreferencesRepository(context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Configuração do Json do kotlinx.serialization
        val json = Json {
            ignoreUnknownKeys = true // Ignora campos extras como "length" no JSON
            isLenient = true // Permite JSON mais flexível
        }
        return Retrofit.Builder()
            .baseUrl("https://dummy/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun providePhraseApiService(retrofit: Retrofit): PhraseApiService {
        return retrofit.create(PhraseApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "phrase_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePhraseDao(database: AppDatabase): PhraseDao {
        return database.phraseDao()
    }
}