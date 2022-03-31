package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC009_StatusCode_200 {

    /*
        Test case #9
            1. Go to “https://practice- cybertekschool.herokuapp.com”
            2. And click on “Status Codes”.
            3. Then click on “200”.
            4. Verify that following message is displayed: “This page returned a 200 status code”
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
    public void StatusCode200(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Status Codes")).click();
        WebElement statusCode200 = driver.findElement(By.linkText("200"));
        statusCode200.click();
        WebElement verify200 = driver.findElement(By.tagName("p"));
        String expected = verify200.getText();
        System.out.println("expected = " + expected);
    }
}
