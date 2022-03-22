package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC001_NotValidDOB {
    /*
       * Test case #1
            1. Go to “https://practice-cybertekschool.herokuapp.com”
            2. Click on “Registration Form”
            3. Enter “wrong_dob” into date of birth input box.
            4. Verify that warning message is displayed: “The date of birth is not valid”
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
    public void registrationFrom_NotValid(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        WebElement registrationForm = driver.findElement(By.xpath("//a[contains(text(),'Registration')]"));
        registrationForm.click();
        WebElement dateOfBirth = driver.findElement(By.xpath("//input[@name='birthday']"));
        dateOfBirth.sendKeys("1234");
        WebElement messageOfBirth = driver.findElement(By.xpath("//small[contains(text(),'birth is not valid')]"));
        String expectationMessage =  messageOfBirth.getText();
        String actualMessage = "The date of birth is not valid";
        Assert.assertEquals(actualMessage,expectationMessage);
    }
}
