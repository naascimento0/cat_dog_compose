package com.example.catdogincompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catdogincompose.screen.MainScreen
import com.example.catdogincompose.screen.PhraseScreen
import com.example.catdogincompose.ui.theme.CatDogInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CatDogInComposeTheme {
                val navController = rememberNavController()
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val savedName = sharedPreferences.getString("userName", null)

                Scaffold(
                    Modifier.fillMaxSize(),
                    containerColor = colorResource(R.color.blue),
                ) {
                    padding ->
                    NavHost(
                        navController = navController,
                        startDestination = if (savedName != null) "phraseScreen/$savedName" else "mainScreen",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("mainScreen") {
                            MainScreen(
                                modifier = Modifier.fillMaxSize(),
                                onSaveClick = { name ->
                                    navController.navigate("phraseScreen/$name")
                                }
                            )
                        }
                        composable("phraseScreen/{userName}") {
                                backStackEntry -> val userName = backStackEntry.arguments?.getString("userName") ?: "Visitante"
                            PhraseScreen(
                                modifier = Modifier.fillMaxSize(),
                                userName = userName
                            )
                        }
                    }
                }
            }
        }
    }
}