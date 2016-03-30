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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuestionnaireApplication.class, Drivers.class})
@WebAppConfiguration
@IntegrationTest(value = {"server.port:0", "spring.mongodb.embedded.version=3.1.6"})
@ActiveProfiles("initial,firefox")
public class FullFlowTest {
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
    public void testFullFlow() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.id("identifier")).clear();
        driver.findElement(By.id("identifier")).sendKeys("Selenium");
        driver.findElement(By.id("identifier")).clear();
        driver.findElement(By.id("identifier")).sendKeys("Selenium Автотестер, который использует все страницы");
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("10");
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
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("1337");
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("100000");
        driver.findElement(By.xpath("(//label[@for='gender-female'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("35");
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-marvell'])")).click();
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("352");
        driver.findElement(By.xpath("(//label[@for='gender-female'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-nothing'])")).click();
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys("3521");
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-marvell'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.xpath("(//label[@for='gender-female'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-marvell'])")).click();
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.xpath("(//label[@for='gender-female'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-nothing'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-anime'])")).click();
        driver.findElement(By.xpath("(//label[@for='gender-male'])")).click();
        driver.findElement(By.xpath("(//label[@for='domain-cosplay'])")).click();
        driver.findElement(By.xpath("(//label[@for='interest-both'])")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.name("1")).click();
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
        driver.findElement(By.xpath("(//input[@name='22'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='23'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='24'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='25'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='26'])[2]")).click();
        driver.findElement(By.name("27")).click();
        driver.findElement(By.xpath("(//input[@name='28'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='29'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='30'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='31'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='32'])[6]")).click();
        driver.findElement(By.name("33")).click();
        driver.findElement(By.xpath("(//input[@name='34'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='35'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='36'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='37'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='38'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='39'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='40'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='41'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='42'])[2]")).click();
        driver.findElement(By.name("43")).click();
        driver.findElement(By.xpath("(//input[@name='44'])[2]")).click();
        driver.findElement(By.name("45")).click();
        driver.findElement(By.xpath("(//input[@name='46'])[3]")).click();
        driver.findElement(By.name("47")).click();
        driver.findElement(By.xpath("(//input[@name='48'])[4]")).click();
        driver.findElement(By.name("49")).click();
        driver.findElement(By.xpath("(//input[@name='50'])[5]")).click();
        driver.findElement(By.name("51")).click();
        driver.findElement(By.xpath("(//input[@name='52'])[6]")).click();
        driver.findElement(By.name("53")).click();
        driver.findElement(By.xpath("(//input[@name='54'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='55'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='56'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='57'])[3]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.name("-953602642")).click();
        driver.findElement(By.xpath("(//input[@name='1513655014'])[2]")).click();
        driver.findElement(By.name("858144922")).click();
        driver.findElement(By.xpath("(//input[@name='271277637'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='1837081339'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='1556360619'])[2]")).click();
        driver.findElement(By.name("1756580015")).click();
        driver.findElement(By.xpath("(//input[@name='1065581970'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='-1712505029'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='95815518'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='-1913685798'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='89587178'])[2]")).click();
        driver.findElement(By.name("708252017")).click();
        driver.findElement(By.xpath("(//input[@name='1947120838'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='-1391395154'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='1599379222'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='-1404855792'])[5]")).click();
        driver.findElement(By.xpath("(//input[@name='-149648585'])[4]")).click();
        driver.findElement(By.xpath("(//input[@name='1569928400'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='122197542'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='-149648585'])[6]")).click();
        driver.findElement(By.xpath("(//input[@name='1569928400'])[7]")).click();
        driver.findElement(By.xpath("(//input[@name='122197542'])[7]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Спасибо за участие в опросе!", driver.findElement(By.cssSelector("p.mainblock")).getText());
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
