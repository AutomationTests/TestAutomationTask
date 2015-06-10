package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by pboiko on 6/9/2015.
 */
public class CreatingDeletingGoogleDriveFolder {

    private WebDriver driver;
    private Logger logger;
    private String url;
    private String login;
    private String password;
    private final String CLASS_NAME = this.getClass().getName();

    @BeforeTest
    public void initializeClass() {
        logger = Logger.getLogger(CreatingDeletingGoogleDriveFolder.class.getName());
        logger.info("Test suite " + CLASS_NAME + " is started");
        url = "https://www.google.com.ua/";
        login = "autotesttask@gmail.com";
        password = "123zxcqweasd";
    }

    @BeforeClass
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
    public void creatingFolderGoogleDrive () throws InterruptedException {
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@id='gbwa']/div[1]/a")).click();

        logger.info("List of available google options is open");

        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@id='gb49']/span[1]")).click();

        logger.info("Google drive is clicked and opened");

        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"drive_main_page\"]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/div/div/div/div/div[1]")).click();

        logger.info("list of new options is open");

        Thread.sleep(1000);

        driver.findElement(By.className("j-D")).findElement(By.className("j-D-s")).click();

        logger.info("creating folder is started");

        Thread.sleep(1000);

        WebElement creationFolderWindow = driver.switchTo().activeElement();

        String folderName = "MyFolder";

        creationFolderWindow.sendKeys(folderName);

        creationFolderWindow.sendKeys(Keys.ENTER);

        logger.info("folder with name - " + folderName + " is created");

        Thread.sleep(2000);

        WebElement createdFolder =  driver.switchTo().activeElement();

        Assert.assertEquals(createdFolder.getText(), folderName);

        logger.info("Creation folder test passed successfuly");
}

    @AfterTest
    public void deletetingFolder () throws InterruptedException {

        driver.findElement(By.cssSelector("#drive_main_page > div > div.a-pa-ob-yd-aa.a-pa-ob-yd-aa-Zk.a-pa-ob-hg.a-jb-hg >" +
                " div:nth-child(1) > div > div > div > div.a-pa-ob-yd-aa-J.a-pa-ob-yd-aa-J-Ff.a-pa-ob-fi-J.a-R-K-Ro-m > " +
                "div > div > div > div:nth-child(2) > div > div.a-pa-ob-yd-aa-J.a-pa-ob-yd-aa-J-em.a-Ra-Bd.a-pa-Ra-Zi > " +
                "div > div:nth-child(4)")).click();

        logger.info("folder was removed");
        logger.info("Test suite " + CLASS_NAME + " is finished");
        driver.quit();
    }
}
