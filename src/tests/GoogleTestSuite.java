package tests;

import com.google.api.client.googleapis.GoogleUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by pboiko on 6/9/2015.
 */
public class GoogleTestSuite {

    private WebDriver driver;
    private Logger logger;
    private String url;
    private String login;
    private String password;
    private String className = this.getClass().getName();

    @BeforeClass
    public void initializeClass() {
        logger = Logger.getLogger(GoogleTestSuite.class.getName());
        logger.info("Test suite " + className + " is started");
        url = "https://www.google.com.ua/";
        login = "autotesttask@gmail.com";
        password = "123zxcqweasd";
    }

    @BeforeMethod
    public void loginToGoogle() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("driver is initialized");
        driver.get(url);

        logger.info("url " + url + " is open");

        driver.findElement(By.id("gb_70")).click();

        WebElement loginField = driver.findElement(By.id("Email"));

        logger.info("page wiht login field is open");

        if (loginField.getText() != "") {
            loginField.clear();
        }

        loginField.sendKeys(login);

        logger.info(login + " is inputed to login field");

        driver.findElement(By.id("next")).click();

        logger.info("button next was clicked");

        driver.findElement(By.id("Passwd")).sendKeys(password);

        logger.info("password was inputed to password filed");

        driver.findElement(By.id("signIn")).click();

        logger.info("user wiht login - " + login + " was signed in");
    }

    @Test
    public void authenticationCompleteTest () {
        WebElement profileLabel = driver.findElement(By.xpath(".//*[@id='gbw']/div/div/div[2]/div[4]/div[1]/a"));
        Assert.assertEquals(profileLabel.getText(), login);

        logger.info("test finished successfully");

        driver.close();
    }

    @Test
    public void creatingFolderGoogleDrive () throws InterruptedException {
        Thread.sleep(3000);

        driver.findElement(By.xpath(".//*[@id='gbwa']/div[1]/a")).click();

        logger.info("List of available google options is open");

        Thread.sleep(3000);

        driver.findElement(By.xpath(".//*[@id='gb49']/span[1]")).click();

        logger.info("Google drive is clicked and opened");

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"drive_main_page\"]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/div/div")).click();

        logger.info("list of new options is open");

        List<WebElement> googleDriveOptions =  driver.findElements(By.tagName("div"));

        driver.findElement(By.id(":4m")).click();

        for (WebElement temp : googleDriveOptions) {
            if (temp != null && temp.getAttribute("role") != null && temp.getAttribute("role").equals("menuitem")) {
                if (temp.getAttribute("id").equals("4m")) {
                    temp.submit();
                }
            }
        }

        logger.info("creating folder is started");

    }

    @AfterClass
    public void shutDown () {
        logger.info("Test suite " + className + " is finished");
        driver.quit();
    }



}
