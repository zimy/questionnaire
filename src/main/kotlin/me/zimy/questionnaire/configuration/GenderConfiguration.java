package me.zimy.questionnaire.configuration;

import me.zimy.questionnaire.domain.Gender;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class to read properties about gender names from configuration and provide them if necessary
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/16/14.
 */
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


    public String getGenderText(Gender gender) {
        String result = null;
        switch (gender) {
            case Gender.Male:
                result = getMale();
                break;
            case Gender.Female:
                result = getFemale();
                break;
        }
        return result;
    }
}
