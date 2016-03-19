package me.zimy.questionnaire.domain

import org.hibernate.validator.constraints.Length
import java.util.*

data class Responder(var id: String? = null,
                     var age: Long = -1,
                     var domain: Domain = Domain.Nothing,
                     var gender: Gender = Gender.Male,
                     var interest: Interest = Interest.Nothing,
                     @Length(min = 1) var identifier: String = "",
                     var responses: MutableList<Response> = ArrayList(),
                     var thirdStages: MutableList<ThirdStageResponse> = ArrayList())