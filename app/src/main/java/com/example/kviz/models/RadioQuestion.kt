package com.example.kviz.models

data class RadioQuestion(
    val questionType : Int,
    val question : String,
    val answerChoices : List<String>,
    val correctAnswers : String
)
