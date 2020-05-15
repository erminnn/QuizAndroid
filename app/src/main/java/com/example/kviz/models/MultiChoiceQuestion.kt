package com.example.kviz.models

data class MultiChoiceQuestion(
    val question : String,
    val answerChoices : List<MultiChoiceItem>,
    val correctAnswers : List<MultiChoiceItem>
)