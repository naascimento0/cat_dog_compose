package com.example.catdogincompose.di

import android.content.Context
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
- * Module that provides dependencies for the app.
+ * **AppModule**
+ *
+ * This Dagger module provides application-wide dependencies. It configures and
+ * provides singleton instances of core components like `PhraseRepository`,
+ * `UserPreferencesRepository`, `Retrofit`, and `PhraseApiService`. These
+ * dependencies are scoped to the `SingletonComponent`, meaning they are
+ * created once and shared throughout the application's lifecycle.
 */
@Module // anotação que indica que essa classe é um módulo do Dagger
@InstallIn(SingletonComponent::class) // anotação que indica que as dependências desse módulo são instâncias únicas
object AppModule {

    @Provides // anotação que indica que esse métodoo fornece uma dependência
    @Singleton // anotação que indica que a dependência é única e durará por toda a vida do app
    fun providePhraseRepository(phraseApiService: PhraseApiService): PhraseRepository {
        return PhraseRepository(phraseApiService)
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
}