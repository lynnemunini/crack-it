package com.grayseal.triviaapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grayseal.triviaapp.screens.QuizScreen
import com.grayseal.triviaapp.screens.SplashScreen

@Composable
fun QuizNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = QuizScreens.SplashScreen.name){
        composable(QuizScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(QuizScreens.QuizScreen.name) {
            QuizScreen(navController = navController)
        }
    }
}