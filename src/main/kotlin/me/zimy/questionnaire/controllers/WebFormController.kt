package me.zimy.questionnaire.controllers

import com.google.common.base.Strings
import me.zimy.questionnaire.DataSaver
import me.zimy.questionnaire.domain.Responder
import me.zimy.questionnaire.repositories.QuestionRepository
import me.zimy.questionnaire.repositories.ResponderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
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
    fun firstStep(session: HttpSession): String {
        session.invalidate()
        logger.info("/ GET asked")
        return "commonQuestions"
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST), params = arrayOf("identifier", "age", "gender", "domain", "interest"))
    fun checkInputFromFirst(@Valid responder: Responder, model: Model, session: HttpSession, response: HttpServletResponse): String {
        val savedResponder = responderService!!.save(responder)
        logger.info("Responder:" + responder.identifier + " (" + responder.gender + ", " + responder.domain + ", " + responder.age + ") #" + savedResponder.id)
        model.addAttribute("questions", questionService!!.getByTargetGender(responder.gender))
        session.setAttribute("id", savedResponder.id)
        response.addCookie(Cookie("id", savedResponder.id))
        logger.info("Processing with session id = ", session.getAttribute("id").toString())
        return "standardQuestionnaire"
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST))
    @Throws(InterruptedException::class)
    fun getSecondStageRequest(@RequestParam params: Map<String, String>, session: HttpSession, request: HttpServletRequest): String {
        if (Strings.isNullOrEmpty(session.getAttribute("id") as String?)) {
            logger.error("Binding error on second POST")
            return "commonQuestions"
        }

        val cookieId = request.cookies.filter { one -> "id".equals(one.name) }
        val sessionId = session.getAttribute("id") as String
        logger.info("Processing with session id = ", session.getAttribute("id"))
        dataSaver!!.handleSendData(params, sessionId)
        logger.info("#$sessionId completed the survey instance")
        return "thanks"
    }
}
