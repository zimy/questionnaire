package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.repositories.QuestionRepository;
import me.zimy.questionnaire.repositories.ResponderRepository;
import me.zimy.questionnaire.repositories.ThirdStagePairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ThirdStagePairRepository thirdStagePairRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String firstStep(Model model, Responder responder) {
        logger.info("/ GET asked");
        return "firstPage";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"identifier", "age", "gender", "domain"})
    public String checkInputFromFirst(@Valid Responder responder, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.error("Binding errors on first POST");
            return "firstPage";
        }
        Responder saved = responderService.save(responder);
        logger.info("Responder:" + saved.getIdentifier() + " (" + saved.getGender() + ", " + saved.getDomain() + ", " + saved.getAge() + ") #" + saved.getId());
        model.addAttribute("questions", questionService.getByTargetGender(saved.getGender()));
        model.addAttribute("userId", saved.getId());
        return "secondPage";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getSecondStageRequest(@RequestParam Map<String, String> params, @ModelAttribute("userId") String userId) throws InterruptedException {
        if (userId == null) {
            logger.error("Binding error on second POST");
            return "firstPage";
        }
        dataSaver.handleSendData(params, userId);
        logger.info("#" + userId + " completed the survey instance");
        return "thirdPage";
    }

    @RequestMapping(value = "/",params = "any_shit")
    public String getThirdStageRequest(@ModelAttribute("userId") String userId) {
        if (userId == null) {
            logger.error("Error of id on third stage saving");
            return "firstPage";
        }

        return "fourthPage";
    }
}