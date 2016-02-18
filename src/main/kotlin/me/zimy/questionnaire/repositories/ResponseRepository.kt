package me.zimy.questionnaire.repositories

import me.zimy.questionnaire.domain.Response
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by zimy on 2/18/16.
 */
interface ResponseRepository : MongoRepository<Response, Long>