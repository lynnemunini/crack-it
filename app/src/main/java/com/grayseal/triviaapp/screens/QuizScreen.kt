package com.grayseal.triviaapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Cancel
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grayseal.triviaapp.R
import com.grayseal.triviaapp.navigation.QuizScreens
import com.grayseal.triviaapp.ui.theme.sonoFamily
import com.grayseal.triviaapp.utils.shakeClickEffect

@Composable
fun QuizScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colors.secondary)
        ) {
        CancelButton(navController = navController)
        QuestionCard()
    }

}

@Composable
fun CancelButton(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top){
        FilledTonalButton(
            onClick = {navController.navigate(route = QuizScreens.QuizScreen.name)},
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

@Composable
fun QuestionCard(){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            QuestionIllustration()
            Text("Who invented water?")
        }
    }
}

@Composable
fun QuestionIllustration(){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp),
        horizontalArrangement = Arrangement.Start) {
        Image(
            painter = painterResource(id = R.drawable.fall),
            contentDescription = "Question Illustration"
        )
    }
}