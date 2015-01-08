package me.zimy.questionnaire;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import mails.NotificationPreparator;
import me.zimy.questionnaire.configuration.*;
import me.zimy.questionnaire.domain.Responder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.file.Path;
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
public class Mailer implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(Mailer.class);
    @Autowired
    DomainConfiguration domainConfiguration;
    @Autowired
    GenderConfiguration genderConfiguration;
    @Autowired
    LikenessConfiguration likenessConfiguration;
    @Autowired
    RecipientList recipientList;
    @Autowired
    NotificationConfiguration notificationConfiguration;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailDaemonConfiguration senderConfiguration;
    @Autowired
    RequestReportConfiguration requestReportConfiguration;
    @Autowired
    ScheduledReportConfiguration scheduledReportConfiguration;
    private ApplicationContext applicationContext;

    void notifyOnResponderDone(Responder responder) {
        try {
            this.mailSender.send(applicationContext.getBean(NotificationPreparator.class));
        } catch (Exception ex) {
            logger.error("Can't email: " + ex.getMessage());
        }
    }

    public void emailReport(Path path, boolean requested) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            Date date = new Date();
            DateFormat df = new ISO8601DateFormat();
            String format = df.format(date);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(recipientList.getRecipients().toArray(new String[recipientList.getRecipients().size()]));
            mimeMessageHelper.addAttachment("Report at " + format + ".ods", path.toFile());
            mimeMessageHelper.setFrom(senderConfiguration.getEmail());
            if (requested) {
                mimeMessageHelper.setSubject(requestReportConfiguration.getSubject());
                mimeMessageHelper.setText(String.format(requestReportConfiguration.getText(), format));
            } else {
                mimeMessageHelper.setSubject(scheduledReportConfiguration.getSubject());
                mimeMessageHelper.setText(String.format(scheduledReportConfiguration.getText(), format));
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("Can't email: " + e.getMessage());

        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
