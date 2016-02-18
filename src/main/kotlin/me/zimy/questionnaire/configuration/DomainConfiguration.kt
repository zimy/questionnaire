package me.zimy.questionnaire.configuration

import me.zimy.questionnaire.domain.Domain
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/16/14.
 */
@Configuration
@ConfigurationProperties(prefix = "domain")
open class DomainConfiguration {
    var nothing = "neither anime nor cosplay"
    var anime = "anime"
    var cosplay = "cosplay"
    var both = "anime and cosplay"
    fun getDomainText(domain: Domain): String {
        var result: String? = null
        when (domain) {
            Domain.Nothing -> result = nothing
            Domain.Anime -> result = anime
            Domain.Cosplay -> result = cosplay
            Domain.Both -> result = both
        }
        return result
    }
}
