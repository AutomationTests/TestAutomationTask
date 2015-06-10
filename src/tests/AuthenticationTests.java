package tests;

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

import java.util.concurrent.TimeUnit;

/**
 * Created by pboiko on 6/10/2015.
 */
public class AuthenticationTests {

    private WebDriver driver;
    private Logger logger;
    private String url;
    private String login;
    private String password;
    private final String CLASS_NAME = this.getClass().getName();

    @BeforeClass
    public void initializeClass() {
        logger = Logger.getLogger(CLASS_NAME);
        logger.info("Test suite " + CLASS_NAME + " is started");
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
    public void signOutTest () throws InterruptedException {
        driver.findElement(By.xpath(".//*[@id='gbw']/div/div/div[2]/div[4]/div[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div/div/div/div[2]/div[4]/div[2]/div[3]/div[2]/a")).click();

        logger.info("sign out is successful");

        Assert.assertEquals(driver.findElement(By.id("gb_70")).getText(), "Sign in");

        logger.info("test finished successfully");

        driver.close();
    }

    @AfterClass
    public void shutDown () {
        logger.info("Test suite " + CLASS_NAME + " is finished");

        driver.quit();
    }
}
