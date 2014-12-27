package me.zimy.questionnaire.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Configures Spring MailSender (uses JavaMail API) to send emails
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/15/14.
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailSenderConfiguration {

    Logger logger = LoggerFactory.getLogger(MailSenderConfiguration.class);
    String host = "";
    Integer port = 0x0FACADED;
    String username = "";
    String password = "";
    String email = "";
    String theme = "Questionnaire completed.";
    String text = "Questionnaire done. Respondent #%1$d: %2$s (%3$s, %4$s, %5d). \nAnswers:\n%6$s";
    boolean ssl = false;
    boolean debug = false;
    List<String> recipients = new ArrayList<>();

    @Bean
    public JavaMailSender mailSender() {
        logger.info("Mailer configured with server " + host + ":" + port);
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.**ssl.enable", String.valueOf(ssl));
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.**ssl.required", String.valueOf(ssl));
        if (debug) {
            javaMailSender.getJavaMailProperties().setProperty("mail.debug", "true");
        }
        javaMailSender.getJavaMailProperties().setProperty("mail.transport.protocol", ssl ? "smtps" : "smtp");
        return javaMailSender;
    }

    public SimpleMailMessage templateMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setSubject(theme);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
