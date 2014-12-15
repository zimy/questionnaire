package me.zimy.questionnaire;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/14/14.
 */
@Aspect
public class MailOnFinishing {

    Logger logger = LoggerFactory.getLogger(MailOnFinishing.class);

    @Pointcut("execution(* me.zimy.questionnaire.DataSaver.handleSendData(..))")
    public void sendEmail() {
    }

    @After("sendEmail()")
    public void doer() {
        logger.error("ASPECT CALLED!!!");
    }
}
