package me.zimy.questionnaire.configuration

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

/**
 * Ignore this, do not touch it at all. That component allows to serve static content.

 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * *
 * @since 12/1/14.
 */

@EnableWebMvc
@Configuration
open class MVCConfig : WebMvcAutoConfigurationAdapter()
