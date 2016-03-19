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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuestionnaireApplication.class, Drivers.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TestWithoutSecondStage {
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
    public void testMainLine() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.id("identifier")).clear();
        driver.findElement(By.id("identifier")).sendKeys("Selenium");
        driver.findElement(By.id("identifier")).clear();
        driver.findElement(By.id("identifier")).sendKeys("Selenium Автотестер");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1338");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1339");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1340");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1341");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1342");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1343");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1344");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1345");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1346");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1347");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1348");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1349");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1350");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1351");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1333");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("19");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("20");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("21");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("22");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("23");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("24");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("25");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("26");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("27");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("28");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("29");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='gender-female'])")).click();
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-marvell'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-marvell'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.name("1")).click();
        driver.findElement(By.xpath("(//input[@name='2'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='2'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='2'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='3'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='4'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='5'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='6'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='7'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='8'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='9'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='10'])[2]")).click();
        driver.findElement(By.name("11")).click();
        driver.findElement(By.xpath("(//input[@name='12'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='13'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='14'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='15'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='16'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='17'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='18'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='19'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='20'])[2]")).click();
        driver.findElement(By.name("21")).click();
        driver.findElement(By.xpath("(//input[@name='22'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='23'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='24'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='25'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='26'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='27'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='28'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='29'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='30'])[2]")).click();
        driver.findElement(By.name("31")).click();
        driver.findElement(By.xpath("(//input[@name='32'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='33'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='34'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='35'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='36'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='37'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='38'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='39'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='40'])[2]")).click();
        driver.findElement(By.name("41")).click();
        driver.findElement(By.xpath("(//input[@name='42'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='43'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='44'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='45'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='46'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='47'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='48'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='49'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='50'])[2]")).click();
        driver.findElement(By.name("51")).click();
        driver.findElement(By.xpath("(//input[@name='52'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='53'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='54'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='55'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='56'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='57'])[5]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
//        assertEquals("Спасибо за участие в опросе!", driver.findElement(By.cssSelector("p.mainblock")).getText());
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
