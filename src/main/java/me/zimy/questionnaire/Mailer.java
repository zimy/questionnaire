package me.zimy.questionnaire;

import me.zimy.questionnaire.domain.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/15/14.
 */
@Service
public class Mailer {
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
        msg.setText(msg.getText() + " Respondent: " + responder.getIdentifier() + " (" + responder.getGender() + ", " + responder.getDomain() + ", " + responder.getAge() + ", #" + responder.getId() + ")\n" + responder.getResponses());
        try {
            this.mailSender.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
