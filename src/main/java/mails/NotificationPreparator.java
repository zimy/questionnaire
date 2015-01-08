package mails;

import me.zimy.questionnaire.configuration.*;
import me.zimy.questionnaire.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 1/9/15.
 */
@Component
@Scope(value = "prototype")
public class NotificationPreparator implements MimeMessagePreparator {
    private final Logger logger = LoggerFactory.getLogger(NotificationPreparator.class);
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
    Responder responder;

    public NotificationPreparator(Responder responder) {
        this.responder = responder;
    }

    /**
     * Prepare the given new MimeMessage instance.
     *
     * @param mimeMessage the message to prepare
     * @throws javax.mail.MessagingException passing any exceptions thrown by MimeMessage
     *                                       methods through for automatic conversion to the MailException hierarchy
     * @throws java.io.IOException           passing any exceptions thrown by MimeMessage methods
     *                                       through for automatic conversion to the MailException hierarchy
     * @throws Exception                     if mail preparation failed, for example when a
     *                                       Velocity template cannot be rendered for the mail text
     */
    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(recipientList.getRecipients().toArray(new String[recipientList.getRecipients().size()]));
            mimeMessageHelper.setSubject(notificationConfiguration.getSubject());
            mimeMessageHelper.setFrom(senderConfiguration.getEmail());
            StringBuilder sb = new StringBuilder();
            for (Response r : responder.getResponses()) {
                Question question = r.getQuestion();
                sb.append(question.getId()).append(" \'").append(question.getQuestion()).append("\' ").append(getAnswerText(r.getResponse())).append("\n");
            }
            String text = notificationConfiguration.getText();
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
}
