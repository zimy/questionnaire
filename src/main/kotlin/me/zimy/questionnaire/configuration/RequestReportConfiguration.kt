package me.zimy.questionnaire.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "mail.report.request")
open class RequestReportConfiguration {
    var text = ""
    var subject = ""

}