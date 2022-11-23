package com.grayseal.triviaapp.navigation

enum class QuizScreens {
    SplashScreen,
    QuizScreen;

    companion object {
        fun fromRoute(route: String?): QuizScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            QuizScreen.name -> QuizScreen
            null -> SplashScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}