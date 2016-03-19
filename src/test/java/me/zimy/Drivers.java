package me.zimy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by zimy on 3/18/16.
 */
@Configuration
class Drivers {
    @Bean
    @Profile("!firefox")
    WebDriver phantomDriver() {
        return new PhantomJSDriver();
    }

    @Bean
    @Profile("firefox")
    WebDriver firefoxDriver() {
        return new FirefoxDriver();
    }
}
