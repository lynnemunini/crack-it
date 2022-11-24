package com.grayseal.triviaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Cancel
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.grayseal.triviaapp.navigation.QuizScreens

@Composable
fun QuizScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        ) {
        CancelButton(navController = navController)
    }

}

@Composable
fun CancelButton(navController: NavController){
    Row(horizontalArrangement = Arrangement.Start) {
        FilledIconButton(
            onClick = { navController.navigate(route = QuizScreens.QuizScreen.name)},
            shape = CircleShape,
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xfffca82f))) {
            Icon(
                imageVector = Icons.Sharp.Cancel,
                contentDescription = "Cancel",
                tint = Color.White
            )
        }        
    }
}