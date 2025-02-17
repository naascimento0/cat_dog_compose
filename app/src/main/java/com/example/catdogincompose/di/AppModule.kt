package com.example.catdogincompose.di

import android.content.Context
import com.example.catdogincompose.data.repository.PhraseRepository
import com.example.catdogincompose.data.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that provides dependencies for the app.
 */
@Module // anotação que indica que essa classe é um módulo do Dagger
@InstallIn(SingletonComponent::class) // anotação que indica que as dependências desse módulo são instâncias únicas
object AppModule {

    @Provides // anotação que indica que esse métodoo fornece uma dependência
    @Singleton // anotação que indica que a dependência é única e durará por toda a vida do app
    fun providePhraseRepository(): PhraseRepository {
        return PhraseRepository()
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(@ApplicationContext context: Context): UserPreferencesRepository {
        return UserPreferencesRepository(context)
    }
}