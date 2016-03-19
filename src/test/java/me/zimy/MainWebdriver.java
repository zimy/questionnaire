package me.zimy;

import me.zimy.questionnaire.QuestionnaireApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuestionnaireApplication.class, Drivers.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MainWebdriver {
    @Value("${local.server.port}")
    private int serverPort;
    @Autowired
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        baseUrl = "http://localhost:" + serverPort;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testMainWebdriver() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.id("identifier")).clear();
        driver.findElement(By.id("identifier")).sendKeys("Selenium");
        driver.findElement(By.id("identifier")).clear();
        driver.findElement(By.id("identifier")).sendKeys("Selenium Автотестер");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1337");
        driver.findElement(By.xpath("//label[@for='gender-male']")).click();
        driver.findElement(By.xpath("//label[@for='domain-cosplay']")).click();
        driver.findElement(By.xpath("//label[@for='interest-both']")).click();
        driver.findElement(By.xpath("//label[@for='interest-anime']")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.name("1")).click();
        driver.findElement(By.xpath("(//input[@name='2'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='3'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='5'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='11'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='10'])[4]")).click();
        driver.findElement(By.name("8")).click();
        driver.findElement(By.xpath("(//input[@name='9'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='7'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='6'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='4'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='12'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='19'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='18'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='17'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='15'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='16'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='14'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='13'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='27'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='26'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='24'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='21'])[5]")).click();
        driver.findElement(By.name("20")).click();
        driver.findElement(By.xpath("(//input[@name='22'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='23'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='25'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='28'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='29'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='30'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='31'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='32'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='27'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='27'])[2]")).click();
        driver.findElement(By.name("29")).click();
        driver.findElement(By.xpath("(//input[@name='32'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='34'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='33'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='35'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='36'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='37'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='38'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='39'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='41'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='42'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='43'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='44'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='45'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='45'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='46'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='47'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='48'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='49'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='50'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='51'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='52'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='53'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='54'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='55'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='56'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='57'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='40'])[5]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
