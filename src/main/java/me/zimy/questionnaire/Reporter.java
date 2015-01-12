package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.domain.Response;
import me.zimy.questionnaire.reporting.Report;
import me.zimy.questionnaire.services.QuestionService;
import me.zimy.questionnaire.services.ResponderService;
import me.zimy.questionnaire.services.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.table.DefaultTableModel;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/26/14.
 */
@Component
public class Reporter {
    private final Logger logger = LoggerFactory.getLogger(Reporter.class);
    @Autowired
    Mailer mailer;
    @Autowired
    ResponderService responderService;
    @Autowired
    ResponseService responseService;
    @Autowired
    QuestionService questionService;
    volatile List<Response> lastResponse = null;

    @PostConstruct
    public void onConstruct() {
        lastResponse = responseService.getAll();
    }

    @Transactional
    @Scheduled(cron = "0 0 10 * * mon")
    public void sendScheduledReport() {
        List<Response> currentResponses = responseService.getAll();
        if (lastResponse.containsAll(currentResponses) && currentResponses.containsAll(lastResponse)) {
            logger.info("Data not changed since previous reporting. Report will not be generated.");
        } else {
            logger.info("Data changed since previous reporting, starting generation of new report.");
            lastResponse = currentResponses;
            mailer.emailReport(getReport(), false);
        }
    }

    public Report getReported() {
        List<Responder> allResponders = responderService.getAll();
        List<Question> allQuestions = questionService.getAll();
        List<String> names = getActualNames(allResponders);
        String[] columns = new String[]{"Id", "Question text", "Group"};
        String[] allColumns = new String[columns.length + names.size()];
        System.arraycopy(columns, 0, allColumns, 0, columns.length);
        System.arraycopy(names.toArray(new String[names.size()]), 0, allColumns, columns.length, names.size());
        Report report = new Report();
        report.addPage("All", new DefaultTableModel(getData(allQuestions, allResponders, names), allColumns));
        return report;
    }

    private Object[][] getData(List<Question> allQuestions, List<Responder> allResponders, List<String> names) {
        Object[][] data = new Object[allQuestions.size()][3 + names.size()];
        for (int i = 0; i < allQuestions.size(); i++) {
            Question aQuestion = allQuestions.get(i);
            data[i][0] = aQuestion.getId();
            data[i][1] = aQuestion.getQuestion();
            data[i][2] = aQuestion.getCriteria();
            int counter = 0;
            for (Responder responder : allResponders) {
                for (Response response : responder.getResponses()) {
                    if (response.getQuestion().equals(aQuestion)) {
                        data[i][3 + counter] = 1 + response.getResponse().ordinal();
                    }
                }
                if (responder.getResponses().size() != 0) {
                    counter++;
                }
            }
        }
        return data;
    }

    private List<String> getActualNames(List<Responder> allResponders) {
        List<String> names = new ArrayList<>(allResponders.size());
        for (Responder responder : allResponders) {
            if (responder.getResponses().size() != 0) {
                names.add(responder.getIdentifier());
            }
        }
        return names;
    }

    public Path getReport() {
        try {
            Path tempFile = Files.createTempFile("Questionnaire report", ".ods");
            logger.trace("tmp file with report created");
            getReported().export(tempFile);
            return tempFile;
        } catch (IOException | NullPointerException e) {
            logger.error("Error while working with spreadsheet: " + e.getMessage());
            return null;
        }
    }
}
