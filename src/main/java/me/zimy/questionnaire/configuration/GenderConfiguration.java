package me.zimy.questionnaire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Class to read properties about gender names from configuration and provide them if necessary
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/16/14.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "gender")
public class GenderConfiguration {

    String male = "male";
    String female = "female";

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }
}
