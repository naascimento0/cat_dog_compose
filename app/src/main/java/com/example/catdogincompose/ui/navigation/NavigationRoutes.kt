package com.example.catdogincompose.ui.navigation

/**
 * Sealed class that represents the routes in the app.
 */
sealed class Route(val route: String) {
    data object MainScreen : Route("mainScreen")
    data object PhraseScreen : Route("phraseScreen")
}