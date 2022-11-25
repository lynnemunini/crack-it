package com.grayseal.triviaapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.grayseal.triviaapp.R
import com.grayseal.triviaapp.navigation.QuizScreens
import com.grayseal.triviaapp.ui.theme.sonoFamily
import com.grayseal.triviaapp.utils.shakeClickEffect
import com.grayseal.triviaapp.widgets.ProceedButton
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun QuizScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.secondary)
    ) {
        CancelButton(navController = navController)
        QuestionCard(navController = navController)
    }

}

@Composable
fun CancelButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        FilledTonalButton(
            onClick = { navController.navigate(route = QuizScreens.QuizScreen.name) },
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
fun QuestionCard(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .padding(20.dp)
    ) {
        val (illustration, card) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(card) {
                    top.linkTo(illustration.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 120.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(50.dp)
                ){
                Column(modifier = Modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {
                    //Text("NASA was invented in the military?", color = colors.onPrimary, fontSize = 25.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Bold)
                }
                ProceedButton(action = "Next", navController = navController)
            }
        }
        Box(
            modifier = Modifier
                .size(150.dp)
                .constrainAs(illustration) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.fall),
                contentDescription = "Question Illustration"
            )
        }
    }
}
