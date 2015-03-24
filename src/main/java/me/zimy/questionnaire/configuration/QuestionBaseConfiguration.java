package me.zimy.questionnaire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 3/24/15.
 */
@Configuration
@ConfigurationProperties
public class QuestionBaseConfiguration {
    String QuestionFile = "./QuestionList.ods";

    public String getQuestionfile() {
        return QuestionFile;
    }

    public void setQuestionfile(String QuestionFile) {
        this.QuestionFile = QuestionFile;
    }
}
