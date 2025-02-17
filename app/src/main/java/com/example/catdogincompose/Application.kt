package com.example.catdogincompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Classe para inicializar o injetor de dependências Hilt
 *
 * This class is annotated with @HiltAndroidApp to enable Hilt for the app.
 */
@HiltAndroidApp
class CatDogApp : Application()