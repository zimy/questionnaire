package me.zimy.questionnaire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/27/14.
 */
@Configuration
@ConfigurationProperties(prefix = "mail.notification")
public class NotificationConfiguration {
    String text = "";
    String subject = "";

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
