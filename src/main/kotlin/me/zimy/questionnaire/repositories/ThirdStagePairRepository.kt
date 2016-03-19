package me.zimy.questionnaire.repositories

import me.zimy.questionnaire.domain.ThirdStagePair
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by zimy on 3/6/16.
 */
interface ThirdStagePairRepository : MongoRepository<ThirdStagePair, String> {
    fun getByFirstAndSecond(first: String, second: String): ThirdStagePair?
}