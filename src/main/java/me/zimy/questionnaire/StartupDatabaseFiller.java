package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Gender;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.services.QuestionService;
import org.apache.commons.io.FilenameUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
@Component
public class StartupDatabaseFiller implements SmartLifecycle {

    private static Logger logger = LoggerFactory.getLogger(StartupDatabaseFiller.class);
    @Autowired
    QuestionService questionService;
    private boolean running = false;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {

    }

    @Override
    public void start() {
        URL systemResource = ClassLoader.getSystemResource("QuestionList.ods");
        try {
            String baseName = FilenameUtils.getBaseName(systemResource.getFile());
            String extension = FilenameUtils.getExtension(systemResource.getFile());
            File file = File.createTempFile(baseName, extension);
            InputStream in = systemResource.openStream();
            Path target = file.toPath();
            StandardCopyOption[] opts =
                    {StandardCopyOption.REPLACE_EXISTING};
            Files.copy(in, target, opts);
            if (file.exists()) {
                logger.trace("tmp file with questions found");
                final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
                readFromSpreadSheet(sheet);
                running = true;
            } else {
                logger.error("File with questions not found");
            }
        } catch (IOException e) {
            logger.error("Error while working with spreadsheet: " + e.getMessage());
            e.printStackTrace();
            running = false;
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    void readFromSpreadSheet(Sheet sheet) {
        if (!testSpreadSheet(sheet)) {
            logger.error("Incorrect headers in question spreadsheet");
        } else {
            int counter = 1;
            Question question = readQuestionFromStyleSheet(sheet, counter);
            while (question != null) {
                Question lookup = questionService.find(question.getId());
                Question writeResult;
                if (lookup == null) {
                    writeResult = questionService.save(question);
                    logger.trace("Get new question with id==" + question.getId() + " as " + writeResult.getId());
                } else if (!lookup.equals(question)) {
                    writeResult = questionService.save(question);
                    logger.trace("Updated question with id==" + lookup.getId() + " as " + writeResult.getId() + " and original id==" + question.getId());
                } else {
                    logger.trace("Duplicate question found, skipping");
                }
                question = readQuestionFromStyleSheet(sheet, ++counter);
            }
        }
    }

    boolean testSpreadSheet(Sheet sheet) {
        String cellId = getCell(sheet, 0, 0);
        String cellText = getCell(sheet, 0, 1);
        String cellGender = getCell(sheet, 0, 2);
        return cellId.equals("id") && (cellText.equals("question")) && cellGender.equals("targetGender");
    }

    String getCell(Sheet sheet, int y, int x) {
        return sheet.getCellAt(x, y).getTextValue();
    }

    Question readQuestionFromStyleSheet(Sheet sheet, int row) {
        Question result = null;
        String textId = getCell(sheet, row, 0);
        String text = getCell(sheet, row, 1);
        String textGender = getCell(sheet, row, 2);
        if ((!textId.equals("")) && (!textGender.equals(""))) {
            try {
                Long id = Long.parseLong(textId);
                Gender gender = Gender.valueOf(textGender);
                result = new Question(text);
                result.setId(id);
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