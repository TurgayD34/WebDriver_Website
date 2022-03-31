package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC008_AutoComplete {

    /*
      Test case #8
        1. Go to “https://practice-cybertekschool.herokuapp.com”
        2. And click on “Autocomplete”.
        3. Enter “United States of America” into country input box.
        4. Verify that following message is displayed: “You selected: United States of America”
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
    public void AutoComplete(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Autocomplete")).click();
        WebElement autoComplete = driver.findElement(By.xpath("//input[@name='myCountry']"));
        WebElement autoCompleteBtn = driver.findElement(By.xpath("//input[@onclick='log()']"));
        autoComplete.sendKeys("Turkey");
        autoCompleteBtn.click();

        WebElement result = driver.findElement(By.id("result"));
        String rslt = result.getText();
        Assert.assertTrue(result.isDisplayed());
        System.out.println("rslt = " + rslt);

        autoComplete.sendKeys("United States of America");
        autoCompleteBtn.click();
        WebElement result2 = driver.findElement(By.id("result"));
        String rslt2 = result2.getText();
        Assert.assertTrue(result.isDisplayed());
        System.out.println("rslt = " + rslt2);
    }
}
