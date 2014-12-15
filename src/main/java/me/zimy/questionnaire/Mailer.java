package me.zimy.questionnaire;

import me.zimy.questionnaire.configuration.DomainConfiguration;
import me.zimy.questionnaire.configuration.GenderConfiguration;
import me.zimy.questionnaire.configuration.LikenessConfiguration;
import me.zimy.questionnaire.configuration.MailSenderConfiguration;
import me.zimy.questionnaire.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service to send emails on some actions
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/15/14.
 */
@Async
@Service
public class Mailer {
    @Autowired
    DomainConfiguration domainConfiguration;
    @Autowired
    GenderConfiguration genderConfiguration;
    @Autowired
    LikenessConfiguration likenessConfiguration;
    @Autowired
    private MailSender mailSender;
    @Qualifier("mailSenderConfiguration")
    @Autowired
    private MailSenderConfiguration senderConfiguration;
    @Autowired
    private SimpleMailMessage templateMessage;

    void notifyOnResponderDone(Responder responder) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(senderConfiguration.getRecipients().toArray(new String[senderConfiguration.getRecipients().size()]));
        StringBuilder sb = new StringBuilder();
        for (Response r : responder.getResponses()) {
            Question question = r.getQuestion();
            sb.append(question.getId()).append(" \'").append(question.getQuestion()).append("\' ").append(getAnswerText(r.getResponse())).append("\n");
        }
        String text = msg.getText();
        Long id = responder.getId();
        String identifier = responder.getIdentifier();
        String genderText = getGenderText(responder.getGender());
        String domainText = getDomainText(responder.getDomain());
        Long age = responder.getAge();
        String answers = sb.toString();
        String formattedString = String.format(text, id, identifier, genderText, domainText, age, answers);
        msg.setText(formattedString);
        try {
            this.mailSender.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
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
