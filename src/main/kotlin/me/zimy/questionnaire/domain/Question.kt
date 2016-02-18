package me.zimy.questionnaire.domain

data class Question(val id: String, val question: String, val targetGender: Gender, val Criteria: Int, var responseList: List<Response>)