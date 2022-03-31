package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC006_tempMailAddress {

    /*
     Test case #6
        1. Go to "https://www.tempmailaddress.com/"
        2. Copy and save email as a string.
        3. Then go to “https://practice-cybertekschool.herokuapp.com”
        4. And click on “Sign Up For Mailing List".
        5. Enter any valid name.
        6. Enter email from the
        2.Step 7. Click Sign Up
        8. Verify that following message is displayed: “Thank you for signing up.
        Click the button below to return to the home page.”
        9. Navigate back to the “https://www.tempmailaddress.com/”
        10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
        11. Click on that email to open it.
        12. Verify that email is from: “do-not-reply@practice.cybertekschool.com”
        13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
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
    public void tempmailaddress(){
        driver.get("https://www.tempmailaddress.com/");
        WebElement copyEmail = driver.findElement(By.xpath("//span[@id='email']"));
        String getEmail = copyEmail.getText();
        System.out.println("getEmail = " + getEmail);

        //Go to Cybertek
        driver.navigate().to("http://practice.cybertekschool.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Tdemir");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(getEmail);
        driver.findElement(By.xpath("//button[@name='wooden_spoon']")).click();
        WebElement expectedMessage = driver.findElement(By.xpath("//h3[@name='signup_message']"));
        String expected = expectedMessage.getText();
        String actual = "Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual, expected);
    }
}
