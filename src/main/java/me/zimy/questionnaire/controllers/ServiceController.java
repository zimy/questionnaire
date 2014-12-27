package me.zimy.questionnaire.controllers;

import me.zimy.questionnaire.ScheduledReporter;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.services.QuestionService;
import me.zimy.questionnaire.services.ResponderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
@Controller
@RequestMapping(value = "/services")
public class ServiceController {
    @Autowired
    ScheduledReporter reporter;
    @Autowired
    private ResponderService responderService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @RequestMapping(value = "/responders", method = RequestMethod.GET)
    @ResponseBody
    public List<Responder> getAllResponders() {
        return responderService.getAll();
    }

    @RequestMapping(value = "/report")
    @ResponseBody
    public String requestEmailReport() {
        reporter.sendEmailReport();
        return "You will get reported soon.";
    }
}
