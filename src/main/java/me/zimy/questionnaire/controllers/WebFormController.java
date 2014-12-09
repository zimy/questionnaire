package me.zimy.questionnaire.controllers;

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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstStep(Model model, Responder responder) {
        return "commonQuestions";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
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

    @RequestMapping("/end")
    public String thankYou() {
        return "thanks";
    }
}
