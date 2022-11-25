package com.grayseal.triviaapp.repository

import com.grayseal.triviaapp.data.DataOrException
import com.grayseal.triviaapp.model.QuestionItem
import com.grayseal.triviaapp.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionAPI){
    private val listOfQuestions = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()
}