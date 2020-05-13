package com.example.kviz.models

data class MultiChoiceQuestion(
    val questionType : Int,
    val question : String,
    val answerChoices : List<MultiChoiceItem>,
    val correctAnswers : List<MultiChoiceItem>
)