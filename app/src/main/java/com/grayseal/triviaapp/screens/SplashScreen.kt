package com.grayseal.triviaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.grayseal.triviaapp.ui.theme.montserratFamily
import com.grayseal.triviaapp.ui.theme.notoSansFamily
import com.grayseal.triviaapp.ui.theme.sonoFamily

@Composable
fun SplashScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.reading),
            contentDescription = "Splash Screen"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF294d77), fontSize = 35.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Bold)) {
                    append("Learn The Fun Way with ")
                }
                withStyle(style = SpanStyle(color = Color(0xFFfca82f), fontSize = 40.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Bold)) {
                    append("Trivia App")
                }
            },
            modifier = Modifier.padding(20.dp)
        )
        Text(
            "Interactive fun quizzes to help you get into a habit of innovative learning.",
            style = (TextStyle(fontSize = 15.sp, color = Color(0xFFa8bdd5))),
            fontFamily = sonoFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

    }

}