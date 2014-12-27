package me.zimy.questionnaire;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import me.zimy.questionnaire.configuration.*;
import me.zimy.questionnaire.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * Service to send emails on some actions
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/15/14.
 */
@Async
@Service
public class Mailer {
    private final Logger logger = LoggerFactory.getLogger(Mailer.class);
    @Autowired
    DomainConfiguration domainConfiguration;
    @Autowired
    GenderConfiguration genderConfiguration;
    @Autowired
    LikenessConfiguration likenessConfiguration;
    @Autowired
    ReportConfiguration reportConfiguration;
    @Autowired
    private JavaMailSender mailSender;

    @Qualifier("mailSenderConfiguration")
    @Autowired
    private MailSenderConfiguration senderConfiguration;

    void notifyOnResponderDone(Responder responder) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(senderConfiguration.getRecipients().toArray(new String[senderConfiguration.getRecipients().size()]));
            mimeMessageHelper.setSubject(senderConfiguration.getTheme());
            mimeMessageHelper.setFrom(senderConfiguration.getEmail());
            StringBuilder sb = new StringBuilder();
            for (Response r : responder.getResponses()) {
                Question question = r.getQuestion();
                sb.append(question.getId()).append(" \'").append(question.getQuestion()).append("\' ").append(getAnswerText(r.getResponse())).append("\n");
            }
            String text = senderConfiguration.getText();
            Long id = responder.getId();
            String identifier = responder.getIdentifier();
            String genderText = getGenderText(responder.getGender());
            String domainText = getDomainText(responder.getDomain());
            Long age = responder.getAge();
            String answers = sb.toString();
            String formattedString = String.format(text, id, identifier, genderText, domainText, age, answers);
            mimeMessageHelper.setText(formattedString);
            try {
                this.mailSender.send(mimeMessage);
            } catch (Exception ex) {
                logger.error("Can't email: " + ex.getMessage());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void emailReport(File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            Date date = new Date();
            DateFormat df = new ISO8601DateFormat();
            String format = df.format(date);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(senderConfiguration.getRecipients().toArray(new String[senderConfiguration.getRecipients().size()]));
            mimeMessageHelper.setText(String.format(reportConfiguration.getMessage(), format));
            mimeMessageHelper.addAttachment("Report at " + format + ".ods", file);
            mimeMessageHelper.setSubject(reportConfiguration.getTitle());
            mimeMessageHelper.setFrom(senderConfiguration.getEmail());
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("Can't email: " + e.getMessage());

        }
    }

    private String getDomainText(Domain domain) {

        String result = null;
        switch (domain) {
            case Nothing:
                result = domainConfiguration.getNothing();
                break;
            case Anime:
                result = domainConfiguration.getAnime();
                break;
            case Cosplay:
                result = domainConfiguration.getCosplay();
                break;
            case Both:
                result = domainConfiguration.getBoth();
                break;
        }
        return result;
    }

    private String getGenderText(Gender gender) {
        String result = null;
        switch (gender) {
            case Male:
                result = genderConfiguration.getMale();
                break;
            case Female:
                result = genderConfiguration.getFemale();
                break;
        }
        return result;
    }

    private String getAnswerText(Likeness response) {
        String result = null;
        switch (response) {
            case VeryUnlikely:
                result = likenessConfiguration.getVeryUnlikely();
                break;
            case Unlikely:
                result = likenessConfiguration.getUnlikely();
                break;
            case SlightlyUnlikely:
                result = likenessConfiguration.getSlightlyUnlikely();
                break;
            case SlightlyLikely:
                result = likenessConfiguration.getSlightlyLikely();
                break;
            case Likely:
                result = likenessConfiguration.getLikely();
                break;
            case VeryLikely:
                result = likenessConfiguration.getVeryLikely();
                break;
        }
        return result;
    }
}
