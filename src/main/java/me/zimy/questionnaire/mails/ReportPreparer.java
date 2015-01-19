package me.zimy.questionnaire.mails;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import me.zimy.questionnaire.configuration.MailDaemonConfiguration;
import me.zimy.questionnaire.configuration.NotificationConfiguration;
import me.zimy.questionnaire.configuration.RecipientList;
import me.zimy.questionnaire.configuration.RequestReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 1/9/15.
 */
@Component
public class ReportPreparer {
    @Autowired
    private RecipientList recipientList;
    @Autowired
    private MailDaemonConfiguration senderConfiguration;
    @Autowired
    private RequestReportConfiguration requestReportConfiguration;
    @Autowired
    private NotificationConfiguration scheduledReportConfiguration;

    public MimeMessagePreparator requestReport(final Path path, final boolean requested) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                Date date = new Date();
                DateFormat df = new ISO8601DateFormat();
                String format = df.format(date);
                String[] to = recipientList.getRecipients().toArray(new String[recipientList.getRecipients().size()]);
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(to);
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
        };
    }
}
