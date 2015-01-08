package me.zimy.questionnaire;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import me.zimy.questionnaire.configuration.*;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.mails.NotificationPreparer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
public class Mailer {
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
    @Autowired
    NotificationPreparer notificationPreparer;

    void notifyOnResponderDone(final Responder responder) {
        try {
            this.mailSender.send(notificationPreparer.getNotificatiion(responder));
        } catch (Exception ex) {
            logger.error("Can't email: " + ex.getMessage());
        }
    }

    public void emailReport(final Path path, final boolean requested) {
        try {
            mailSender.send(new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
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
                }
            });
        } catch (Exception e) {
            logger.error("Can't email: " + e.getMessage());

        }
    }
}
