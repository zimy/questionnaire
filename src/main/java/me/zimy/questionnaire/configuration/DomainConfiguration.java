package me.zimy.questionnaire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/16/14.
 */
@Configuration
@ConfigurationProperties(prefix = "domain")
public class DomainConfiguration {
    String Nothing = "neither anime nor cosplay";
    String Anime = "anime";
    String Cosplay = "cosplay";
    String Both = "anime and cosplay";

    public String getNothing() {
        return Nothing;
    }

    public void setNothing(String nothing) {
        Nothing = nothing;
    }

    public String getAnime() {
        return Anime;
    }

    public void setAnime(String anime) {
        Anime = anime;
    }

    public String getCosplay() {
        return Cosplay;
    }

    public void setCosplay(String cosplay) {
        Cosplay = cosplay;
    }

    public String getBoth() {
        return Both;
    }

    public void setBoth(String both) {
        Both = both;
    }
}
