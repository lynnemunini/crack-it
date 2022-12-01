package com.grayseal.triviaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.grayseal.triviaapp.component.QuestionsUi
import com.grayseal.triviaapp.navigation.QuizScreens
import com.grayseal.triviaapp.utils.shakeClickEffect


@Composable
fun QuizScreen(navController: NavController, viewModel: QuestionsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.secondary)
    ) {
        CancelButton(navController = navController)
        QuestionsUi(viewModel)
    }
}

@Composable
fun CancelButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        FilledTonalButton(
            onClick = { navController.navigate(route = QuizScreens.SplashScreen.name) },
            modifier = Modifier.shakeClickEffect(),
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            shape = RoundedCornerShape(100.dp),
        ) {
            Icon(
                imageVector = Icons.Sharp.Close,
                modifier = Modifier.size(30.dp),
                contentDescription = "Cancel",
                tint = Color.White
            )
        }
    }
}
