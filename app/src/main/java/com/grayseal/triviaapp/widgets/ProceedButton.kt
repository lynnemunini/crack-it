package com.grayseal.triviaapp.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grayseal.triviaapp.navigation.QuizScreens
import com.grayseal.triviaapp.ui.theme.sonoFamily

@Composable
fun ProceedButton(action: String, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom){
        FilledTonalButton(
            onClick = {navController.navigate(route = QuizScreens.QuizScreen.name)},
            modifier = Modifier
                .height(70.dp)
                .width(150.dp)
                ,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colors.primary),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(3.dp, color = MaterialTheme.colors.onSurface)
        ) {
            Text(action, color = Color.White, fontFamily = sonoFamily, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)

        }
}}