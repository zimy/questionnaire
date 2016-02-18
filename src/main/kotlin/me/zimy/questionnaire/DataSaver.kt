package me.zimy.questionnaire

import me.zimy.questionnaire.domain.Likeness
import me.zimy.questionnaire.domain.Response
import me.zimy.questionnaire.repositories.QuestionRepository
import me.zimy.questionnaire.repositories.ResponderRepository
import me.zimy.questionnaire.repositories.ResponseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.Future

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/10/14.
 */
@Async
@Service
@Transactional(rollbackFor = arrayOf(NumberFormatException::class, NullPointerException::class))
class DataSaver {
    private val logger = LoggerFactory.getLogger(DataSaver::class.java)
    @Autowired
    private val responderService: ResponderRepository? = null
    @Autowired
    private val questionService: QuestionRepository? = null
    @Autowired
    private val responseService: ResponseRepository? = null

    /**
     * This  method is for storing answers of responders.

     * @param params    is a map of params where values with natural numerical keys will be selected and saved as answers
     * *
     * @param sessionId is a Responders id to link responses with.
     */
    @Async
    @Throws(InterruptedException::class)
    fun handleSendData(params: Map<String, String>, sessionId: String): Future<Boolean> {
        logger.info("Starting async work on saving #" + sessionId)
        try {
            val parsedResponderId = java.lang.Long.parseLong(sessionId)
            var parsedQuestionId: Long?
            val responder = responderService!!.findOne(parsedResponderId)
            for (s in params.keys) {
                if (s == "_csrf") {
                    continue
                }
                parsedQuestionId = java.lang.Long.parseLong(s)
                val question = questionService!!.findOne(parsedQuestionId)
                val response = Response("", Likeness.valueOf(params[s]!!), question, responder)
                responder.responses.add(response)
                question.responseList.add(response)
                response.responder = responder
                response.question = question
                questionService.save(question)
                responseService!!.save(response)
            }
            responderService.save(responder)
            logger.info("All answers for responder #$parsedResponderId saved.")
        } catch (e: NumberFormatException) {
            logger.warn("Somebody passed strange data in Id:" + sessionId)
            throw e
        } catch (e: NullPointerException) {
            logger.warn("Somebody passed strange data in Id:" + sessionId)
            throw e
        }

        return AsyncResult(true)
    }
}
