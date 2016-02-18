package me.zimy.questionnaire.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.util.*

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/27/14.
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
open class RecipientList {
    var recipients: List<String> ? = ArrayList()
}
