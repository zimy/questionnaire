package me.zimy.questionnaire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/27/14.
 */
@Configuration
@ConfigurationProperties(prefix = "mail.report")
public class ReportConfiguration {
    String message = "";
    String title = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
