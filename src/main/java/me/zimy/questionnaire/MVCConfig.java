package me.zimy.questionnaire;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;

/**
 * Ignore this, do not touch it at all. That component allows to serve static content.
 *
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */

@EnableWebMvc
@Configuration
public class MVCConfig extends WebMvcAutoConfigurationAdapter {
}
