package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.mails.NotificationPreparer;
import me.zimy.questionnaire.mails.ReportPreparer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

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
    JavaMailSender mailSender;
    @Autowired
    NotificationPreparer notificationPreparer;
    @Autowired
    ReportPreparer reportPreparer;

    void notifyOnResponderDone(final Responder responder) {
        try {
            this.mailSender.send(notificationPreparer.getNotificatiion(responder));
        } catch (Exception ex) {
            logger.error("Can't email: " + ex.getMessage());
        }
    }

    public void emailReport(final Path path, final boolean requested) {
        try {
            mailSender.send(reportPreparer.requestReport(path, requested));
        } catch (Exception e) {
            logger.error("Can't email: " + e.getMessage());

        }
    }
}
