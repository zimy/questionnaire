package me.zimy.questionnaire.domain

/**
 * Created by zimy on 2/18/16.
 */
data class Response(val id: String, var response: Likeness, var question: Question, var responder: Responder)