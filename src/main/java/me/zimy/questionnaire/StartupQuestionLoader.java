package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Gender;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.services.QuestionService;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
@Component
public class StartupQuestionLoader {

    private static Logger logger = LoggerFactory.getLogger(StartupQuestionLoader.class);
    @Autowired
    QuestionService questionService;

    @PostConstruct
    public void start() {
        Path resource = Paths.get(ClassLoader.getSystemResource("QuestionList.ods").getFile());
        try {
            Path target = Files.createTempFile("Questionnaire", ".ods");
            Files.copy(resource, Files.newOutputStream(target));
            if (Files.exists(target)) {
                logger.trace("tmp file with questions found");
                readFromSpreadSheet(SpreadSheet.createFromFile(target.toFile()).getSheet(0));
                Files.deleteIfExists(target);
            } else {
                logger.error("File with questions not found");
            }
        } catch (NullPointerException | IOException e) {
            logger.error("Error while working with spreadsheet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void readFromSpreadSheet(Sheet sheet) {
        if (!testSpreadSheet(sheet)) {
            logger.error("Incorrect headers in question spreadsheet");
        } else {
            int counter = 1;
            Question question = readQuestionFromStyleSheet(sheet, counter);
            while (question != null) {
                Question lookup = questionService.find(question.getQuestion());
                Question writeResult;
                if (lookup == null) {
                    writeResult = questionService.save(question);
                    logger.info("Get new question with id==" + question.getId() + " as " + writeResult.getId());
                } else if (!lookup.equals(question)) {
                    writeResult = questionService.save(question);
                    logger.info("Updated question with id==" + lookup.getId() + " as " + writeResult.getId() + " and original id==" + question.getId());
                } else {
                    logger.debug("Duplicate question found, skipping");
                }
                question = readQuestionFromStyleSheet(sheet, ++counter);
            }
        }
    }

    boolean testSpreadSheet(Sheet sheet) {
        String cellId = getCell(sheet, 0, 0);
        String cellText = getCell(sheet, 0, 1);
        String cellGender = getCell(sheet, 0, 2);
        String cellCriteria = getCell(sheet, 0, 3);
        return cellId.equals("id") && (cellText.equals("question")) && cellGender.equals("targetGender") && cellCriteria.equals("Criteria");
    }

    String getCell(Sheet sheet, int y, int x) {
        return sheet.getCellAt(x, y).getTextValue();
    }

    Question readQuestionFromStyleSheet(Sheet sheet, int row) {
        Question result = null;
        String textId = getCell(sheet, row, 0);
        String textText = getCell(sheet, row, 1);
        String textGender = getCell(sheet, row, 2);
        String textCriteria = getCell(sheet, row, 3);
        if ((!textId.equals("")) && (!textGender.equals("")) && (!textCriteria.equals(""))) {
            try {
                Long id = Long.parseLong(textId);
                Gender gender = Gender.valueOf(textGender);
                Integer criteria = Integer.valueOf(textCriteria);
                result = new Question(textText);
                result.setId(id);
                result.setCriteria(criteria);
                result.setTargetGender(gender);
            } catch (NumberFormatException e) {
                logger.error("Incorrect value of ID in a " + (row + 1) + " row");
            } catch (IllegalArgumentException e) {
                logger.error("Incorrect value of gender in a " + (row + 1) + " row");
            }
        }
        return result;
    }
}
