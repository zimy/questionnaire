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
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/10/14.
 */
@Service
@Async
@Transactional(rollbackFor = {NumberFormatException.class, NullPointerException.class})
public class DataSaver {
    private final Logger logger = LoggerFactory.getLogger(DataSaver.class);
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
     * @param sessionId is a Responders id to link responses with.
     */
    @Async
    public Future<Boolean> handleSendData(Map<String, String> params, String sessionId) throws InterruptedException {
        logger.info("Starting async work on saving #" + sessionId);
        try {
            Long parsedResponderId = Long.parseLong(sessionId);
            Long parsedQuestionId;
            Responder responder = responderService.findOne(parsedResponderId);
            for (String s : params.keySet()) {
                if (s.equals("_csrf")) {
                    continue;
                }
                parsedQuestionId = Long.parseLong(s);
                Question question = questionService.findOne(parsedQuestionId);
                Response response = new Response("", Likeness.valueOf(params.get(s)), question, responder);
                responder.getResponses().add(response);
                question.getResponseList().add(response);
                response.setResponder(responder);
                response.setQuestion(question);
                questionService.save(question);
                responseService.save(response);
            }
            responderService.save(responder);
            logger.info("All answers for responder #" + parsedResponderId + " saved.");
        } catch (NumberFormatException | NullPointerException e) {
            logger.warn("Somebody passed strange data in Id:" + sessionId);
            throw e;
        }
        return new AsyncResult<>(true);
    }
}
