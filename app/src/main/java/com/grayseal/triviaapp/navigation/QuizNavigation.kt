package com.grayseal.triviaapp.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grayseal.triviaapp.screens.QuestionsViewModel
import com.grayseal.triviaapp.screens.QuizScreen
import com.grayseal.triviaapp.screens.SplashScreen

@Composable
fun QuizNavigation() {
    val navController = rememberNavController()
    val viewModel: QuestionsViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = QuizScreens.SplashScreen.name){
        composable(QuizScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(QuizScreens.QuizScreen.name) {
            QuizScreen(navController = navController, viewModel)
        }
    }
}