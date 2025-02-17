package com.example.catdogincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catdogincompose.data.repository.UserPreferencesRepository
import com.example.catdogincompose.ui.navigation.Route
import com.example.catdogincompose.ui.navigation.navigateToPhraseScreen
import com.example.catdogincompose.ui.screen.MainScreen
import com.example.catdogincompose.ui.screen.PhraseScreen
import com.example.catdogincompose.ui.theme.CatDogInComposeTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity is the entry point of the application.
 * It sets up the navigation and theme for the app.
 *
 * This activity uses Hilt for dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // permite que o app use a tela inteira

        // inicializa o respositorio do shared preferences
        val userPreferencesRepository = UserPreferencesRepository(applicationContext)
        val savedUserName = userPreferencesRepository.getUserName()

        // define o conteudo da tela
        setContent {
            CatDogInComposeTheme {
                val navController = rememberNavController() // controla a navegaçao entre telas
                // launched effect permite executar coroutines dentro de um composable, serve para executar efeitos colaterais
                LaunchedEffect(savedUserName) { // se o nome do usuario for salvo, navega para a tela de frases
                    if (savedUserName.isNotEmpty()) {
                        navController.navigateToPhraseScreen(savedUserName)
                    }
                }
                Scaffold(
                    Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    padding ->
                    NavHost( // define as rotas de navegaçao
                        navController = navController,
                        startDestination = Route.MainScreen.route,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(Route.MainScreen.route) {
                            MainScreen(
                                modifier = Modifier.fillMaxSize(),
                                onSaveClick = { name ->
                                    navController.navigateToPhraseScreen(name)
                                }
                            )
                        }
                        composable(
                            route = "${Route.PhraseScreen.route}/{userName}",
                            arguments = listOf(navArgument("userName") { // define quais argumentos a rota espera receber
                                defaultValue = "Guest" // "essa tela espera um argumento chamado userName. Se ele nao for fornecido, use Guest como padrao."
                            })
                        ) { backStackEntry ->
                            val userName = backStackEntry.arguments?.getString("userName") ?: "Guest" // no backStackEntry, voce acessa o valor ja resolvido do argumento. Se um valor foi passado na navegaçao, você pega esse valor. Se nao foi passado, ele pega o default definido no arguments.
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