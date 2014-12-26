package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.domain.Response;
import me.zimy.questionnaire.services.QuestionService;
import me.zimy.questionnaire.services.ResponderService;
import me.zimy.questionnaire.services.ResponseService;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.table.DefaultTableModel;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/26/14.
 */
@Component
public class ScheduledReporter {
    private final Logger logger = LoggerFactory.getLogger(ScheduledReporter.class);
    @Autowired
    ResponderService responderService;
    @Autowired
    ResponseService responseService;
    @Autowired
    QuestionService questionService;
    volatile List<Response> lastResponse;

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void schedule() {
        logger.info("Daily report generation");
        List<Response> currentResponses = responseService.getAll();
        if (currentResponses.equals(lastResponse)) {
            logger.info("Data not changed since previous reporting.");
        } else {
            logger.info("Data changed since previous reporting, starting generating.");
            lastResponse = currentResponses;
            List<Responder> allResponders = responderService.getAll();
            List<Question> allQuestions = questionService.getAll();
            List<String> names = new ArrayList<>(allResponders.size());
            for (Responder responder : allResponders) {
                names.add(responder.getIdentifier());
            }
            String[] columns = new String[]{"Id", "Question text", "Group"};
            String[] allColumns = new String[columns.length + names.size()];
            System.arraycopy(columns, 0, allColumns, 0, columns.length);
            System.arraycopy(names.toArray(new String[names.size()]), 0, allColumns, columns.length, names.size());


            try {
                File file = File.createTempFile("report", "ods");
                if (file.exists()) {
                    logger.trace("tmp file with report created");
                    SpreadSheet.createEmpty(new DefaultTableModel(allColumns, allQuestions.size() + 1)).saveAs(file);

                    file.delete();
                } else {
                    logger.error("tmp file with report cannot be created");
                }
            } catch (IOException | NullPointerException e) {
                logger.error("Error while working with spreadsheet: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
