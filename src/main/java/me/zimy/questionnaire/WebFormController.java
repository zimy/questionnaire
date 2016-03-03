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
import org.springframework.web.servlet.ModelAndView;

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
@SessionAttributes("id")
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
    public ModelAndView checkInputFromFirst(@Valid Responder responder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Binding errors on first POST");
            return new ModelAndView("commonQuestions");
        }
        Responder saved = responderService.save(responder);
        logger.info("Responder:" + saved.getIdentifier() + " (" + saved.getGender() + ", " + saved.getDomain() + ", " + saved.getAge() + ") #" + saved.getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("questions", questionService.getByTargetGender(saved.getGender()));
        logger.info("User get attribute = " + saved.getId());
        mv.addObject("id", saved.getId());
        mv.setViewName("standardQuestionnaire");
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getSecondStageRequest(@RequestParam Map<String, String> params, @ModelAttribute String id, SessionStatus status, HttpSession session) throws InterruptedException {
        if (id == null) {
            logger.error("Binding error on second POST");
            return "commonQuestions";
        }
        logger.info("Session set value = " + id);
        logger.info("Session set value = " + session.getAttribute("id"));

        dataSaver.handleSendData(params, id);
        logger.info("#" + id + " completed the survey instance");
        status.setComplete();
        return "thanks";
    }
}
