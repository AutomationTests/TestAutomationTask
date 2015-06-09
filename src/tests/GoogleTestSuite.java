package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.Assert;

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

    @BeforeClass
    public void initializeClass() {
        logger = Logger.getLogger(GoogleTestSuite.class.getName());
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        logger.info("driver is initialized");
    }

    @BeforeMethod
    public void initializeMethod() {
        url = "https://www.google.com.ua/";
        login = "autotesttask@gmail.com";
        password = "123zxcqweasd";
    }

   /* @Test
    public void authenticationTest () {
        driver.get(url);

        logger.info("url " + url + " is open");

        driver.findElement(By.xpath("/*//*[@id=\"gb_70\"]")).click();

        WebElement loginField = driver.findElement(By.xpath("./*//*[@id='Email']"));

        if (loginField.getText() != null) {
            loginField.clear();
        }

        loginField.sendKeys(login);

        driver.findElement(By.xpath("./*//*[@id='next']")).click();

        driver.findElement(By.xpath("./*//*[@id='Passwd']")).sendKeys(password);

        driver.findElement(By.xpath("./*//*[@id='signIn']")).click();

        WebElement profileLabel = driver.findElement(By.className("gb_E"));
        Assert.assertEquals(profileLabel.getText(), login);

    }*/

    @Test
    public void search () {
        driver.get(url);
        driver.findElement(By.xpath("/*//*[@id=\"gb_70\"]")).click();

        WebElement loginField = driver.findElement(By.xpath("./*//*[@id='Email']"));

        if (loginField.getText() != null) {
            loginField.clear();
        }

        loginField.sendKeys(login);

        driver.findElement(By.xpath("./*//*[@id='next']")).click();

        driver.findElement(By.xpath("./*//*[@id='Passwd']")).sendKeys(password);

        driver.findElement(By.xpath("./*//*[@id='signIn']")).click();

        List<WebElement> elements = driver.findElements(By.className("gb_E"));

        for(WebElement temp : elements) {
            System.out.println(temp.getText());
        }

    }

    @AfterClass
    public void shutDown () {
        driver.quit();
    }

}
