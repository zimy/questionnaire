package me.zimy.questionnaire.domain

import org.hibernate.validator.constraints.Length

data class Responder(var id: Long, var age: Long, var domain: Domain, var gender: Gender, var interest: Interest, @Length(min = 1) var identifier: String, var responses: MutableList<Response>)