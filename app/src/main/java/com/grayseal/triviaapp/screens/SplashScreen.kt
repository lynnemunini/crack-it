package com.grayseal.triviaapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grayseal.triviaapp.R
import com.grayseal.triviaapp.navigation.QuizScreens
import com.grayseal.triviaapp.ui.theme.montserratFamily
import com.grayseal.triviaapp.ui.theme.notoSansFamily
import com.grayseal.triviaapp.ui.theme.sonoFamily
import com.grayseal.triviaapp.utils.bounceClick
import com.grayseal.triviaapp.utils.shakeClickEffect

@Composable
fun SplashScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(id = R.drawable.reading),
            contentDescription = "Splash Screen"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = colors.onPrimary, fontSize = 35.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Bold)) {
                    append("Learn The Fun Way with ")
                }
                withStyle(style = SpanStyle(color = colors.primary, fontSize = 40.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Bold)) {
                    append("Trivia App")
                }
            },
            modifier = Modifier.padding(20.dp)
        )
        Text(
            "Interactive fun quizzes to help you learn interesting facts and shocking truths and help you get into a habit of learning.",
            style = (TextStyle(fontSize = 15.sp, color = colors.primaryVariant)),
            fontFamily = sonoFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
        }
    StartButton(action = "Start Quiz", navController = navController)
    }

@Composable
fun StartButton(action: String, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom){
        FilledTonalButton(
            onClick = {navController.navigate(route = QuizScreens.QuizScreen.name)},
            modifier = Modifier
                .height(70.dp)
                .width(150.dp)
            ,
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.material.MaterialTheme.colors.primary),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(3.dp, color = androidx.compose.material.MaterialTheme.colors.onSurface)
        ) {
            Text(action, color = Color.White, fontFamily = sonoFamily, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)

        }
    }}