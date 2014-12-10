package me.zimy.questionnaire.controllers;

import me.zimy.questionnaire.DataSaver;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.services.QuestionService;
import me.zimy.questionnaire.services.ResponderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Main controller
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Controller
@RequestMapping("/")
public class WebFormController {

    Logger logger = LoggerFactory.getLogger(WebFormController.class);

    @Autowired
    private ResponderService responderService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private DataSaver dataSaver;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstStep(Model model, Responder responder) {
        return "commonQuestions";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"identifier", "age", "gender", "domain"})
    public String checkInputFromFirst(@Valid Responder responder, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "commonQuestions";
        }
        logger.info("Responder:" + responder.getIdentifier() + " (" + responder.getGender() + ", " + responder.getDomain() + ", " + responder.getAge() + ")");
        responderService.save(responder);
        model.addAttribute("questions", questionService.getByGender(responder.getGender()));
        session.setAttribute("id", responder.getId());
        return "standardQuestionnaire";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getSecondStageRequest(@RequestParam Map<String, String> params, HttpSession session) throws InterruptedException {
        if (session.getAttribute("id") == null) {
            return "commonQuestions";
        }
        String sessionId = String.valueOf(session.getAttribute("id"));
        Future<Boolean> booleanFuture = dataSaver.handleSendData(params, sessionId);
        logger.info("Somebody completed the survey instance with id==" + sessionId);
        return "thanks";
    }
}
