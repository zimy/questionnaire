package me.zimy.questionnaire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Class to read properties about likeness from configuration and provide them if necessary
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/16/14.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "likeness")
public class LikenessConfiguration {
    String VeryUnlikely = "very unlikely";
    String Unlikely = "unlikely";
    String SlightlyUnlikely = "slightly unlikely";
    String SlightlyLikely = "slightly likely";
    String Likely = "likely";
    String VeryLikely = "very likely";

    public String getVeryUnlikely() {
        return VeryUnlikely;
    }

    public void setVeryUnlikely(String veryUnlikely) {
        VeryUnlikely = veryUnlikely;
    }

    public String getUnlikely() {
        return Unlikely;
    }

    public void setUnlikely(String unlikely) {
        Unlikely = unlikely;
    }

    public String getSlightlyUnlikely() {
        return SlightlyUnlikely;
    }

    public void setSlightlyUnlikely(String slightlyUnlikely) {
        SlightlyUnlikely = slightlyUnlikely;
    }

    public String getSlightlyLikely() {
        return SlightlyLikely;
    }

    public void setSlightlyLikely(String slightlyLikely) {
        SlightlyLikely = slightlyLikely;
    }

    public String getLikely() {
        return Likely;
    }

    public void setLikely(String likely) {
        Likely = likely;
    }

    public String getVeryLikely() {
        return VeryLikely;
    }

    public void setVeryLikely(String veryLikely) {
        VeryLikely = veryLikely;
    }
}
