package com.grayseal.triviaapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grayseal.triviaapp.model.QuestionItem
import com.grayseal.triviaapp.screens.QuestionsViewModel
import com.grayseal.triviaapp.ui.theme.sonoFamily

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loading == true) {
        ProgressIndicator()
    } else {
        if (questions != null) {
            QuestionDisplay(question = questions.first())
        }
    }
}

@Composable
fun ProgressIndicator() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = colors.primary)
    }
}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    //questionIndex: MutableState<Int>,
    //viewModel: QuestionsViewModel,
    onNextClicked: (Int) -> Unit = {}
) {

    val choicesState = remember(question) {
        question.choices.toMutableList()
    }
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)

    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == question.answer
        }
    }
    Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp, end = 20.dp)) {
        Text(
            text = question.question, modifier = Modifier
                .align(Alignment.Start)
                .fillMaxHeight(0.3f),
            fontSize = 17.sp, color = Color.Black, fontWeight = FontWeight.Bold, lineHeight = 22.sp
        )

        choicesState.forEachIndexed { index, answerText ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(
                        width = 4.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFe4f0fe),
                                Color(0xFFe4f0fe),
                                Color(0xFFe4f0fe)
                            )
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 50,
                            topEndPercent = 50,
                            bottomEndPercent = 50,
                            bottomStartPercent = 50
                        )
                    )
                    .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (answerState.value == index), onClick = {
                        updateAnswer(index)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                            colors.primary
                        } else {
                            Color.White
                        }
                    )
                )
                Text(text = answerText)
            }
        }

    }
}

@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Question $counter/$outOf",
            style = (TextStyle(fontSize = 18.sp, color = Color.White)),
            fontFamily = sonoFamily,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
    }
}
