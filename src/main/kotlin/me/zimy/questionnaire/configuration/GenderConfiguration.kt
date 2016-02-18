package me.zimy.questionnaire.configuration

import me.zimy.questionnaire.domain.Gender
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Class to read properties about gender names from configuration and provide them if necessary

 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/16/14.
 */
@Configuration
@ConfigurationProperties(prefix = "gender")
open class GenderConfiguration {
    var male = "male"
    var female = "female"
    fun getGenderText(gender: Gender): String {
        var result: String? = null
        when (gender) {
            Gender.Male -> result = male
            Gender.Female -> result = female
        }
        return result
    }
}
