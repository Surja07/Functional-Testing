package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set path to chromedriver if not in system PATH
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void validLoginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("student");
        passwordField.sendKeys("Password123");
        submitButton.click();

        WebElement successMessage = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(successMessage.getText(), "Logged In Successfully");
    }

    @Test
    public void invalidLoginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("wronguser");
        passwordField.sendKeys("wrongpass");
        submitButton.click();

        WebElement errorMsg = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMsg.getText().contains("Your username is invalid!"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
