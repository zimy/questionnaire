package me.zimy.questionnaire.repositories

import me.zimy.questionnaire.domain.Responder
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by zimy on 2/18/16.
 */
interface ResponderRepository : MongoRepository<Responder, String>