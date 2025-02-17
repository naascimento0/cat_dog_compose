package com.example.catdogincompose.ui.navigation

import androidx.navigation.NavController

/**
 * Extension function to navigate to the Phrase screen.
 *
 * @param userName The name of the user.
 */
fun NavController.navigateToPhraseScreen(userName: String) {
    navigate("${Route.PhraseScreen.route}/$userName")
}

