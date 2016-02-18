package me.zimy.questionnaire.configuration

import me.zimy.questionnaire.domain.Likeness
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Class to read properties about likeness from configuration and provide them if necessary

 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/16/14.
 */
@Configuration
@ConfigurationProperties(prefix = "likeness")
open class LikenessConfiguration {
    var veryUnlikely = "very unlikely"
    var unlikely = "unlikely"
    var slightlyUnlikely = "slightly unlikely"
    var slightlyLikely = "slightly likely"
    var likely = "likely"
    var veryLikely = "very likely"
    fun getAnswerText(response: Likeness): String {
        var result: String? = null
        when (response) {
            Likeness.VeryUnlikely -> result = veryUnlikely
            Likeness.Unlikely -> result = unlikely
            Likeness.SlightlyUnlikely -> result = slightlyUnlikely
            Likeness.SlightlyLikely -> result = slightlyLikely
            Likeness.Likely -> result = likely
            Likeness.VeryLikely -> result = veryLikely
        }
        return result
    }
}
