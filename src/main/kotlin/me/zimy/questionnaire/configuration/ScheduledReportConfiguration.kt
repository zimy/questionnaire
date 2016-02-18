package me.zimy.questionnaire.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "mail.report.scheduled")
open class ScheduledReportConfiguration {
    var text = ""
    var subject = ""

}