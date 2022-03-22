package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC004_CharactersLong {
    /*
    Test case #3
    1. Go to “https://practice-cybertekschool.herokuapp.com”
    2. Click on “Registration Form”
    3. Enter only one alphabetic character into first name input box.
    4. Verify that warning message is displayed: “first name must be more than 2 and less than 64 characters long”
*/
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void charactersLong() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        WebElement registrationForm = driver.findElement(By.xpath("//a[contains(text(),'Registration')]"));
        registrationForm.click();
        WebElement lastName = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastName.sendKeys("a");
        WebElement message = driver.findElement(By.xpath("//small[contains(text(),'last name must')]"));
        String expectedMessage = message.getText();
        String actualMessage = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actualMessage,expectedMessage);
    }
}
