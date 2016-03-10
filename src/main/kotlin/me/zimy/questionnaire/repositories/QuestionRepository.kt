package me.zimy.questionnaire.repositories

import me.zimy.questionnaire.domain.Gender
import me.zimy.questionnaire.domain.Question
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by zimy on 2/18/16.
 */
interface QuestionRepository : MongoRepository<Question, String> {
    @Cacheable fun getByTargetGender(gender: Gender): List<Question>
    @Cacheable fun getByQuestion(question: String): Question
}