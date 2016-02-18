package me.zimy.questionnaire.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.validator.constraints.Length

data class Responder(var id: Long, var age: Long, var domain: Domain, var gender: Gender, @Length(min = 1) var identifier: String, @JsonManagedReference var responses: List<Response>)