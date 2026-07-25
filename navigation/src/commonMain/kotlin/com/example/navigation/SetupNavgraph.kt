package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutrisport.utils.Screen
import com.nutrisport.feature.ui.AuthScreen
import com.nutrisport.home.HomeGraphScreen

@Composable
fun SetupNavGraph(startDestination: Screen = Screen.Auth) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Auth> {
            //TODO: why is this not navigating
            AuthScreen(
                navigateToHome = {
                    navController.navigate(Screen.Auth) {
                        popUpTo<Screen.Auth> { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.HomeGraph> {
            HomeGraphScreen(
                navigateToAuth = {
                    navController.navigate(Screen.Auth) {
                        // Clear the backstack so the user can't go back to Home after signing out
                        popUpTo(Screen.HomeGraph) { inclusive = true }
                    }
                }
            )
        }
    }
}
