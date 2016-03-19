package me.zimy.questionnaire.repositories

import me.zimy.questionnaire.domain.Responder
import org.springframework.data.repository.Repository

/**
 * Created by zimy on 2/18/16.
 */
interface ResponderRepository : Repository<Responder, String> {
    fun findOne(id: String): Responder
    fun save(responder: Responder): Responder
}