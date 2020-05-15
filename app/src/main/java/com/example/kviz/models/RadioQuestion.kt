package com.example.kviz.models

data class RadioQuestion(
    val question : String,
    val answerChoices : List<String>,
    val correctAnswer : String
)
