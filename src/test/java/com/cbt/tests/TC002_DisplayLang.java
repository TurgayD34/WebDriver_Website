package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC002_DisplayLang {
    /*
        Test case #2
        1. Go to “https://practice-cybertekschool.herokuapp.com”
        2. Click on “Registration Form”
        3. Verify that following options for programming languages are displayed: c++, java, JavaScript
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
    public void displayLang(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        WebElement registrationForm = driver.findElement(By.xpath("//a[contains(text(),'Registration')]"));
        registrationForm.click();
        WebElement checkbox1 = driver.findElement(By.cssSelector("#inlineCheckbox1"));
        WebElement checkbox2 = driver.findElement(By.cssSelector("#inlineCheckbox2"));
        WebElement checkbox3 = driver.findElement(By.cssSelector("#inlineCheckbox3"));
        Assert.assertFalse(checkbox1.isSelected());
        Assert.assertFalse(checkbox2.isSelected());
        Assert.assertFalse(checkbox3.isSelected());
        checkbox1.click();
        checkbox2.click();
        checkbox3.click();
        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());
        Assert.assertTrue(checkbox3.isSelected());
    }
}
