package me.zimy.questionnaire.repositories

import me.zimy.questionnaire.domain.Gender
import me.zimy.questionnaire.domain.Question
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by zimy on 2/18/16.
 */
interface QuestionRepository : MongoRepository<Question, Long> {
    fun getByTargetGender(gender: Gender): List<Question>
    fun getByQuestion(question: String): Question
}