package com.grayseal.triviaapp.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grayseal.triviaapp.screens.QuestionsViewModel
import com.grayseal.triviaapp.ui.theme.sonoFamily

@Composable
fun Questions(viewModel: QuestionsViewModel){
    val questions = viewModel.data.value.data?.toMutableList()
    if(viewModel.data.value.loading == true){
        ProgressIndicator()
    }
    else{
        Text("Loading STOPPED")
        questions?.forEach { questionItem ->
            Log.d("Result", "Questions: ${questionItem.question}")
        }
        Text("Questions: ${questions?.size}")
    }
}

@Composable
fun ProgressIndicator(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 100.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(color = colors.primary)
    }
}

@Composable
fun QuestionDisplay(){
    Column() {
        
    }
}
@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100){
    Row(modifier = Modifier
        .fillMaxWidth()) {
        Text(text = "Question $counter/$outOf",
            style = (TextStyle(fontSize = 18.sp, color = Color.White)),
            fontFamily = sonoFamily,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp))
    }
}