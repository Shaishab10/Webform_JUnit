import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebFormJUnit {

    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Check if Form successfully submitted")

    @Test
    public void webForm() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();  //accept all cookies
        driver.findElement(By.id("edit-name")).sendKeys("TestUser007"); //name
        driver.findElement(By.id("edit-number")).sendKeys("12345678"); //number
        driver.findElements(By.className("ui-button")).get(1).click(); //age
        WebElement dateElement = driver.findElement(By.id("edit-date"));
        dateElement.click();
        dateElement.sendKeys("09");  //month
        dateElement.sendKeys("28"); //date
        dateElement.sendKeys("2023"); //year
        driver.findElement(By.id("edit-email")).sendKeys("testuser007@gmail.com"); //email
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Testing Junit with selenium"); //textBox
        Utils.scroll(driver,0,500);
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/1.jpg"); //uploadImage
        Thread.sleep(2000);
        driver.findElement(By.id("edit-age")).click(); //checkBox
        driver.findElement(By.id("edit-submit")).click();  //submit
        String messageConfirmation = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(messageConfirmation.contains("Thank you for your submission!"));
    }


    @AfterAll
    public void quitBrowser(){
//        driver.quit();
    }
}
