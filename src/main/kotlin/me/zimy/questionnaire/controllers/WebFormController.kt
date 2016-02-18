package me.zimy.questionnaire.controllers

import me.zimy.questionnaire.DataSaver
import me.zimy.questionnaire.domain.Responder
import me.zimy.questionnaire.repositories.QuestionRepository
import me.zimy.questionnaire.repositories.ResponderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpSession
import javax.validation.Valid

/**
 * Main controller

 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/1/14.
 */
@Controller
@RequestMapping("/")
class WebFormController {

    private val logger = LoggerFactory.getLogger(WebFormController::class.java)

    @Autowired
    private val responderService: ResponderRepository? = null

    @Autowired
    private val questionService: QuestionRepository? = null

    @Autowired
    private val dataSaver: DataSaver? = null

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun firstStep(model: Model, responder: Responder): String {
        logger.info("/ GET asked")
        return "commonQuestions"
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.HEAD))
    fun Head(model: Model, responder: Responder): String {
        logger.info("/ HEAD asked")
        return "commonQuestions"
    }


    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST), params = arrayOf("identifier", "age", "gender", "domain"))
    fun checkInputFromFirst(@Valid responder: Responder, bindingResult: BindingResult, model: Model, session: HttpSession): String {
        if (bindingResult.hasErrors()) {
            logger.error("Binding errors on first POST")
            return "commonQuestions"
        }
        responderService!!.save(responder)
        logger.info("Responder:" + responder.identifier + " (" + responder.gender + ", " + responder.domain + ", " + responder.age + ") #" + responder.id)
        model.addAttribute("questions", questionService!!.getByTargetGender(responder.gender))
        session.setAttribute("id", responder.id)
        return "standardQuestionnaire"
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST))
    @Throws(InterruptedException::class)
    fun getSecondStageRequest(@RequestParam params: Map<String, String>, session: HttpSession): String {
        if (session.getAttribute("id") == null) {
            logger.error("Binding error on second POST")
            return "commonQuestions"
        }
        val sessionId = session.getAttribute("id").toString()
        dataSaver!!.handleSendData(params, sessionId)
        logger.info("#$sessionId completed the survey instance")
        return "thanks"
    }
}
