package me.zimy.questionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@EntityScan
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Questionnaire {
    public static void main(String[] args) {
        SpringApplication.run(Questionnaire.class, args);
    }
}
