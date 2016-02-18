package me.zimy.questionnaire.configuration

import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

/**
 * Configures Spring MailSender (uses JavaMail API) to send emails

 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/15/14.
 */
@Configuration
@ConfigurationProperties(prefix = "mail.daemon")
open class MailDaemonConfiguration {

    internal var logger = LoggerFactory.getLogger(MailDaemonConfiguration::class.java)
    internal var host = ""
    internal var port: Int? = 0x0FACADED
    internal var username = ""
    internal var password = ""
    var email = ""
    internal var ssl = false
    internal var debug = false
    @Bean
    open fun mailSender(): JavaMailSender {
        logger.info("Mailer configured with server $host:$port")
        val javaMailSender = JavaMailSenderImpl()
        javaMailSender.host = host
        javaMailSender.port = port!!
        javaMailSender.username = username
        javaMailSender.password = password
        javaMailSender.javaMailProperties.setProperty("mail.smtp.auth", "true")
        javaMailSender.javaMailProperties.setProperty("mail.smtp.starttls.enable", "true")
        javaMailSender.javaMailProperties.setProperty("mail.smtp.**ssl.enable", ssl.toString())
        javaMailSender.javaMailProperties.setProperty("mail.smtp.**ssl.required", ssl.toString())
        if (debug) {
            javaMailSender.javaMailProperties.setProperty("mail.debug", "true")
        }
        javaMailSender.javaMailProperties.setProperty("mail.transport.protocol", if (ssl) "smtps" else "smtp")
        return javaMailSender
    }
}
