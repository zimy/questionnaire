package me.zimy.questionnaire.controllers;

import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.domain.Response;
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

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.validation.Valid;
import java.util.List;

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
    private ResponderRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstStep(Responder responder) {
        return "commonQuestions";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String checkInputFromFirst(@Valid Responder responder, BindingResult bindingResult, ServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "commonQuestions";
        }
        logger.info("Responder:" + responder.getAge() + " " + responder.getDomain() + " " + responder.getGender() + " " + responder.getIdentifier());
        return "thanks";
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public String thirdStep(Model model, ServletRequest request, ServletResponse response, @RequestParam List<Response> responseList) {
        return "some";
    }
}
