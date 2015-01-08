package me.zimy.questionnaire.mails;

import me.zimy.questionnaire.configuration.*;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 1/9/15.
 */
@Component
public class NotificationPreparer {
    @Autowired
    private RecipientList recipientList;
    @Autowired
    private NotificationConfiguration notificationConfiguration;
    @Autowired
    private MailDaemonConfiguration senderConfiguration;
    @Autowired
    private LikenessConfiguration likenessConfiguration;
    @Autowired
    private GenderConfiguration genderConfiguration;
    @Autowired
    private DomainConfiguration domainConfiguration;

    public MimeMessagePreparator getNotificatiion(final Responder responder) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper;
                mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(recipientList.getRecipients().toArray(new String[recipientList.getRecipients().size()]));
                mimeMessageHelper.setSubject(notificationConfiguration.getSubject());
                mimeMessageHelper.setFrom(senderConfiguration.getEmail());
                StringBuilder sb = new StringBuilder();
                for (Response r : responder.getResponses()) {
                    Question question = r.getQuestion();
                    sb.append(question.getId()).append(" \'").append(question.getQuestion()).append("\' ").append(likenessConfiguration.getAnswerText(r.getResponse())).append("\n");
                }
                String text = notificationConfiguration.getText();
                Long id = responder.getId();
                String identifier = responder.getIdentifier();
                String genderText = genderConfiguration.getGenderText(responder.getGender());
                String domainText = domainConfiguration.getDomainText(responder.getDomain());
                Long age = responder.getAge();
                String answers = sb.toString();
                String formattedString = String.format(text, id, identifier, genderText, domainText, age, answers);
                mimeMessageHelper.setText(formattedString);
            }
        };
    }
}
