package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.*;
import me.zimy.questionnaire.repositories.QuestionRepository;
import me.zimy.questionnaire.repositories.ResponderRepository;
import me.zimy.questionnaire.repositories.ThirdStagePairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 *         *
 * @since 12/10/14.
 */
@Async
@Service
class DataSaver {
    private Logger logger = LoggerFactory.getLogger(DataSaver.class);
    @Autowired
    private ResponderRepository responderService;
    @Autowired
    private QuestionRepository questionService;
    @Autowired
    private ThirdStagePairRepository thirdStages;

    /**
     * This  method is for storing answers of responders.
     *
     * @param params    is a map of params where values with natural numerical keys will be selected and saved as answers
     *                  *
     * @param responder
     */

    Future<Boolean> handleSecondPageData(Map<String, String> params, Responder responder) {
        logger.info("Starting async work on saving #" + responder.getId());
        try {
            for (String s : params.keySet()) {
                if ("_csrf".equals(s)) {
                    continue;
                }
                Question question = questionService.findOne(s);
                Response response = new Response("", Likeness.valueOf(params.get(s)), question);
                responder.getResponses().add(response);
                response.setQuestion(question);
            }
            responderService.save(responder);
            logger.info("All second page data for responder #" + responder.getId() + " saved.");
        } catch (NumberFormatException | NullPointerException e) {
            logger.warn("Somebody passed strange data in Id:" + responder.getId());
            throw e;
        }

        return new AsyncResult<>(true);
    }

    Future<Boolean> handleThirdPageData(Map<String, String> params, Responder responder) {
        for (String s : params.keySet()) {
            if ("_csrf".equals(s)) {
                continue;
            }
            ThirdStagePair thirdStage = thirdStages.findOne(s);
            ThirdStageResponse third = new ThirdStageResponse("", Likeness.valueOf(params.get(s)), thirdStage);
            responder.getThirdStages().add(third);
            third.setThirdPair(thirdStage);
        }
        responderService.save(responder);
        logger.info("All second page data for responder #" + responder.getId() + " saved");
        return new AsyncResult<>(true);
    }
}

