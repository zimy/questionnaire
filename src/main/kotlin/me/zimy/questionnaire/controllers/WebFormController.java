package me.zimy.questionnaire.controllers;

import me.zimy.questionnaire.DataSaver;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.repositories.QuestionRepository;
import me.zimy.questionnaire.repositories.ResponderRepository;
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

/**
 * Main controller
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Controller
@RequestMapping("/")
public class WebFormController {

    private Logger logger = LoggerFactory.getLogger(WebFormController.class);

    @Autowired
    private ResponderRepository responderService;

    @Autowired
    private QuestionRepository questionService;

    @Autowired
    private DataSaver dataSaver;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstStep(Model model, Responder responder) {
        logger.info("/ GET asked");
        return "commonQuestions";
    }

    @RequestMapping(value = "/", method = RequestMethod.HEAD)
    public String Head(Model model, Responder responder) {
        logger.info("/ HEAD asked");
        return "commonQuestions";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"identifier", "age", "gender", "domain"})
    public String checkInputFromFirst(@Valid Responder responder, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            logger.error("Binding errors on first POST");
            return "commonQuestions";
        }
        responderService.save(responder);
        logger.info("Responder:" + responder.getIdentifier() + " (" + responder.getGender() + ", " + responder.getDomain() + ", " + responder.getAge() + ") #" + responder.getId());
        model.addAttribute("questions", questionService.getByTargetGender(responder.getGender()));
        session.setAttribute("id", responder.getId());
        return "standardQuestionnaire";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getSecondStageRequest(@RequestParam Map<String, String> params, HttpSession session) throws InterruptedException {
        if (session.getAttribute("id") == null) {
            logger.error("Binding error on second POST");
            return "commonQuestions";
        }
        String sessionId = String.valueOf(session.getAttribute("id"));
        dataSaver.handleSendData(params, sessionId);
        logger.info("#" + sessionId + " completed the survey instance");
        return "thanks";
    }
}
