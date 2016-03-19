package me.zimy.questionnaire.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Keeps file to third stage CSV
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 3/19/16.
 */
@Configuration
@ConfigurationProperties
open class ThirdStageBaseConfiguration {
    var thirdStageFile = "szho.csv"
}
