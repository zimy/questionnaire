package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Likeness;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.domain.Response;
import me.zimy.questionnaire.repositories.QuestionRepository;
import me.zimy.questionnaire.repositories.ResponderRepository;
import me.zimy.questionnaire.repositories.ResponseRepository;
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
    private ResponseRepository responseService;

    /**
     * This  method is for storing answers of responders.
     *
     * @param params    is a map of params where values with natural numerical keys will be selected and saved as answers
     *                  *
     * @param sessionId is a Responders id to link responses with.
     */

    Future<Boolean> handleSendData(Map<String, String> params, String sessionId) {
        logger.info("Params: " + params.toString());
        logger.info("userId: " + sessionId);
        logger.info("Starting async work on saving #$sessionId");
        try {
            Responder responder = responderService.findOne(sessionId);
            for (String s : params.keySet()) {
                if ("_csrf".equals(s)) {
                    continue;
                }
                Question question = questionService.findOne(s);
                Response response = new Response("", Likeness.valueOf(params.get(s)), question);
                responder.getResponses().add(response);
                question.getResponseList().add(response);
                response.setQuestion(question);
                questionService.save(question);
                responseService.save(response);
            }
            responderService.save(responder);
            logger.info("All answers for responder #$sessionId saved.");
        } catch (NumberFormatException | NullPointerException e) {
            logger.warn("Somebody passed strange data in Id:" + sessionId);
            throw e;
        }

        return new AsyncResult<>(true);
    }
}
