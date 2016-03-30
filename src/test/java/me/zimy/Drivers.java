package me.zimy;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

/**
 * Testing beans. But how to specify profile for tests, who knew?
 * Created by zimy on 3/18/16.
 */
@Configuration
class Drivers {
    @Bean
    @Scope("prototype")
    @Profile("phantom")
    WebDriver phantomDriver() {
        PhantomJsDriverManager.getInstance().setup();
        return new PhantomJSDriver();
    }

    @Bean
    @Scope("prototype")
    @Profile("firefox")
    WebDriver firefoxDriver() {
        return new FirefoxDriver();
    }

    @Bean
    @Scope("prototype")
    @Profile("default")
    WebDriver htmlUnit(){
        return new org.openqa.selenium.htmlunit.HtmlUnitDriver();
    }
}
