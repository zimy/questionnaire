package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.repositories.QuestionRepository;
import me.zimy.questionnaire.repositories.ResponderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
@SessionAttributes("userId")
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

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"identifier", "age", "gender", "domain"})
    public String checkInputFromFirst(@Valid Responder responder, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.error("Binding errors on first POST");
            return "commonQuestions";
        }
        Responder saved = responderService.save(responder);
        logger.info("Responder:" + saved.getIdentifier() + " (" + saved.getGender() + ", " + saved.getDomain() + ", " + saved.getAge() + ") #" + saved.getId());
        model.addAttribute("questions", questionService.getByTargetGender(saved.getGender()));
        model.addAttribute("userId", saved.getId());
        logger.info("User get attribute = " + saved.getId());
        return "standardQuestionnaire";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getSecondStageRequest(@RequestParam Map<String, String> params, SessionStatus status, @ModelAttribute("userId") String userId) throws InterruptedException {
        if (userId == null) {
            logger.error("Binding error on second POST");
            return "commonQuestions";
        }
//        logger.info("Session set value = " + id);
        logger.info("Session set value = " + userId);

        logger.info("Params: " + params.toString());
        logger.info("userId: " + userId);
        dataSaver.handleSendData(params, userId);
        logger.info("#" + userId + " completed the survey instance");
        status.setComplete();
        return "thanks";
    }
}
