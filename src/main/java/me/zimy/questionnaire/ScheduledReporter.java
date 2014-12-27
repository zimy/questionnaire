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

import javax.annotation.PostConstruct;
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
    volatile List<Response> lastResponse = null;

    @PostConstruct
    public void onConstruct() {
        lastResponse = responseService.getAll();
    }

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void schedule() {
        List<Response> currentResponses = responseService.getAll();
        if (lastResponse.containsAll(currentResponses) && currentResponses.containsAll(lastResponse)) {
            logger.info("Data not changed since previous reporting. Report will not be generated.");
        } else {
            logger.info("Data changed since previous reporting, starting generation of new report.");
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

            Object[][] Data = new Object[allQuestions.size()][3 + allResponders.size()];
            for (int i = 0; i < allQuestions.size(); i++) {
                Question aQuestion = allQuestions.get(i);
                Data[i][0] = aQuestion.getId();
                Data[i][1] = aQuestion.getQuestion();
                Data[i][2] = aQuestion.getCriteria();
                for (int i1 = 0; i1 < allResponders.size(); i1++) {
                    Responder responder = allResponders.get(i1);
                    List<Response> responses = responder.getResponses();
                    for (Response response : responses) {
                        if (response.getQuestion().equals(aQuestion)) {
                            Data[i][3 + i1] = 1 + response.getResponse().ordinal();
                        }
                    }
                }
            }

            try {
                File file = File.createTempFile("report", ".ods");
                if (file.exists()) {
                    logger.trace("tmp file with report created");
                    SpreadSheet.createEmpty(new DefaultTableModel(Data, allColumns)).saveAs(file);
                    /*SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
                    msg.setTo(senderConfiguration.getRecipients().toArray(new String[senderConfiguration.getRecipients().size()]));
                    StringBuilder sb = new StringBuilder();
                    for (Response r : responder.getResponses()) {
                        Question question = r.getQuestion();
                        sb.append(question.getId()).append(" \'").append(question.getQuestion()).append("\' ").append(getAnswerText(r.getResponse())).append("\n");
                    }
                    String text = msg.getText();
                    Long id = responder.getId();
                    String identifier = responder.getIdentifier();
                    String genderText = getGenderText(responder.getGender());
                    String domainText = getDomainText(responder.getDomain());
                    Long age = responder.getAge();
                    String answers = sb.toString();
                    String formattedString = String.format(text, id, identifier, genderText, domainText, age, answers);
                    msg.setText(formattedString);
                    try {
                        this.mailSender.send(msg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }*/

                    file.deleteOnExit();
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
