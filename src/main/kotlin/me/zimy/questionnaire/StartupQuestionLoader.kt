package me.zimy.questionnaire

import me.zimy.questionnaire.configuration.QuestionBaseConfiguration
import me.zimy.questionnaire.configuration.ThirdStageBaseConfiguration
import me.zimy.questionnaire.domain.Gender
import me.zimy.questionnaire.domain.Question
import me.zimy.questionnaire.domain.ThirdStagePair
import me.zimy.questionnaire.repositories.QuestionRepository
import me.zimy.questionnaire.repositories.ThirdStagePairRepository
import org.jopendocument.dom.spreadsheet.Sheet
import org.jopendocument.dom.spreadsheet.SpreadSheet
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import javax.annotation.PostConstruct

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/7/14.
 */
@Service
@Profile("initial")
open class StartupQuestionLoader {
    @Autowired
    lateinit private var questions: QuestionRepository
    @Autowired
    lateinit private var questionConfiguration: QuestionBaseConfiguration
    @Autowired
    lateinit private var thirdStagePairs: ThirdStagePairRepository
    @Autowired
    lateinit private var thirdStageBase: ThirdStageBaseConfiguration

    @Suppress("unused")
    @PostConstruct
    fun start() {
        try {
            logger.info("Opening sheet file \"" + questionConfiguration.questionfile + "\"")
            logger.info(questionConfiguration.questionfile)
            logger.info(thirdStageBase.thirdStageFile)
            val inputStream = javaClass.classLoader.getResourceAsStream(questionConfiguration.questionfile)
            val target = Files.createTempFile("Questionnaire", ".ods")
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING)
            if (Files.exists(target)) {
                logger.trace("tmp file with questions found")
                readFromSpreadSheet(SpreadSheet.createFromFile(target.toFile()).getSheet(0))
                Files.deleteIfExists(target)
            } else {
                logger.error("File with questions not found")
            }
            val get = Paths.get(ClassLoader.getSystemResource(thirdStageBase.thirdStageFile).toURI())
            logger.info(get.toString())
            val lines = Files.readAllLines(get)
            lines.map { s ->
                ThirdStagePair(first = s.split(";")[0], second = s.split(";")[1], id = s.hashCode().toString())
            }.forEach { s ->
                var looked = thirdStagePairs.getByFirstAndSecond(s.first, s.second)
                if (looked == null || ( s.first != looked.first && s.second != looked.second)) {
                    val saved = thirdStagePairs.save(s)
                    logger.info("Saved new third stage question with id #" + saved.id)
                }
            }

        } catch (e: NullPointerException) {
            logger.error("Error while working with spreadsheet: " + e.message)
            e.printStackTrace()
        } catch (e: IOException) {
            logger.error("Error while working with spreadsheet: " + e.message)
            e.printStackTrace()
        }

    }

    private fun readFromSpreadSheet(sheet: Sheet) {
        if (!testSpreadSheet(sheet)) {
            logger.error("Incorrect headers in question spreadsheet")
        } else {
            var counter = 1
            var question: Question? = readQuestionFromStyleSheet(sheet, counter)
            while (question != null) {
                val lookup = questions.getByQuestion(question.question)
                val writeResult: Question
                if (lookup != question) {
                    writeResult = questions.save(question)
                    logger.info("Updated question with id==" + writeResult.id + " as " + writeResult.id + " and original id==" + writeResult.id)
                } else {
                    logger.debug("Duplicate question found, skipping")
                }
                question = readQuestionFromStyleSheet(sheet, ++counter)
            }
        }
    }

    private fun testSpreadSheet(sheet: Sheet): Boolean {
        val cellId = getCell(sheet, 0, 0)
        val cellText = getCell(sheet, 0, 1)
        val cellGender = getCell(sheet, 0, 2)
        val cellCriteria = getCell(sheet, 0, 3)
        return cellId == "id" && cellText == "question" && cellGender == "targetGender" && cellCriteria == "Criteria"
    }

    private fun getCell(sheet: Sheet, y: Int, x: Int): String {
        return sheet.getCellAt(x, y).textValue
    }

    private fun readQuestionFromStyleSheet(sheet: Sheet, row: Int): Question? {
        val textId = getCell(sheet, row, 0)
        val textText = getCell(sheet, row, 1)
        val textGender = getCell(sheet, row, 2)
        val textCriteria = getCell(sheet, row, 3)
        if (textId == "" && textText == "" && textGender == "" && textCriteria == "") {
            return null
        }
        try {
            val gender = Gender.valueOf(textGender)
            val criteria = Integer.valueOf(textCriteria)
            return Question(textId, textText, gender, criteria!!)
        } catch (e: NumberFormatException) {
            logger.error("Incorrect value of ID in a " + (row + 1) + " row")
        } catch (e: IllegalArgumentException) {
            logger.error("Incorrect value of gender in a " + (row + 1) + " row")
        }
        return null
    }

    companion object {
        private val logger = LoggerFactory.getLogger(StartupQuestionLoader::class.java)
    }
}
