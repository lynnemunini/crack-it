package com.grayseal.triviaapp.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grayseal.triviaapp.R
import com.grayseal.triviaapp.model.QuestionItem
import com.grayseal.triviaapp.screens.QuestionsViewModel
import com.grayseal.triviaapp.ui.theme.sonoFamily


@Composable
fun QuestionsUi(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember {
        mutableStateOf(0)
    }
    QuestionCard(questionIndex = questionIndex, viewModel, questions)
}

@Composable
fun Questions(
    viewModel: QuestionsViewModel,
    questionIndex: MutableState<Int>,
    questions: MutableList<QuestionItem>?
) {
    if (viewModel.data.value.loading == true) {
        ProgressIndicator()
    } else {
        val question = try {
            questions?.get(questionIndex.value)
        } catch (ex: Exception) {
            null
        }
        if (questions != null) {
            QuestionDisplay(
                question = question!!,
                questionIndex = questionIndex,
            ) {
                questionIndex.value = questionIndex.value + 1
            }
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
    questionIndex: MutableState<Int>,
    onNextClicked: (Int) -> Unit = {},
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
            text = question.question,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp),
            fontSize = 17.sp,
            color = colors.onPrimary,
            fontWeight = FontWeight.Bold,
            fontFamily = sonoFamily,
            lineHeight = 22.sp,
        )
        choicesState.forEachIndexed { index, answerText ->
            Row(
                if (correctAnswerState.value == true && index == answerState.value)
                    Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(bottom = 10.dp)
                        .clip(
                            RoundedCornerShape(10)
                        )
                        .background(color = colors.primary)
                        .clickable {
                            updateAnswer(index)
                        }
                else
                    Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(bottom = 10.dp)
                        .border(
                            width = 0.5.dp,
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
                            RoundedCornerShape(10)
                        )
                        .background(color = Color.Transparent)
                        .clickable {
                            updateAnswer(index)
                        }, verticalAlignment = Alignment.CenterVertically
            ) {
                if (correctAnswerState.value == true && index == answerState.value) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = answerText,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 16.sp,
                        fontFamily = sonoFamily,
                        color = Color.White
                    )
                } else
                    Text(
                        text = answerText,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 16.sp,
                        fontFamily = sonoFamily,
                        color = Color.Gray
                    )
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        FilledTonalButton(
            onClick = { onNextClicked(questionIndex.value) },
            modifier = Modifier
                .height(70.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(3.dp, color = colors.onSurface)
        ) {
            androidx.compose.material.Text(
                "Next",
                color = Color.White,
                fontFamily = sonoFamily,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )
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
            style = (TextStyle(fontSize = 17.sp, color = Color.White)),
            fontFamily = sonoFamily,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
    }
}

@Composable
fun QuestionCard(
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    questions: MutableList<QuestionItem>?
) {
    if (questions != null) {
        QuestionTracker(questionIndex.value, viewModel.getTotalQuestions())
    }
    if (questionIndex.value >= 1) {
        ShowProgress(score = questionIndex.value)
    }
    if (questionIndex.value == 25) {
        questionIndex.value = questionIndex.value + 1
    }

    ConstraintLayout(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
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
                    .padding(bottom = 90.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(50.dp)
            ) {
                DisplayQuestions(
                    questionIndex = questionIndex,
                    viewModel = viewModel,
                    questions = questions
                )
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

@Composable
fun DisplayQuestions(
    viewModel: QuestionsViewModel,
    questionIndex: MutableState<Int>,
    questions: MutableList<QuestionItem>?
) {
    Questions(viewModel = viewModel, questionIndex, questions = questions)
}

@Composable
fun ShowProgress(score: Int) {
    val progressFactor = remember(score) {
        mutableStateOf(score * 0.0005f)
    }
    Row(
        modifier = Modifier
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(10.dp)
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color(0xFF9da1e7)), verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(progressFactor.value),
            enabled = false,
            elevation = null,
            colors = ButtonDefaults.buttonColors(disabledContainerColor = colors.primary)
        ) {

        }
    }
}