package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TC005_SignUp {
    /*
     Test case #5
        1. Go to “https://practice-cybertekschool.herokuapp.com”
        2. Click on “Registration Form”
        3. Enter any valid first name.
        4. Enter any valid last name.
        5. Enter any valid username.
        6. Enter any valid password.
        7. Enter any valid phone number.
        8. Select gender.
        9. Enter any valid date of birth.
        10. Select any department.
        11. Enter any job title.
        12. Select java as a programming language.
        13. Click Sign up.
        14. Verify that following success message is displayed: “You've successfully completed registration!”
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
        WebElement firstname = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstname.sendKeys("John");
        WebElement lastname = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastname.sendKeys("Smith");
        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        username.sendKeys("JohnSmith");
        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("john@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("john1234");
        WebElement phoneNumber = driver.findElement(By.xpath("//input[@name='phone']"));
        phoneNumber.sendKeys("070-012-3434");

        //Select Gender
        WebElement maleGender = driver.findElement(By.xpath("//input[@value='male']"));
        WebElement femaleGender = driver.findElement(By.xpath("//input[@value='female']"));
        WebElement otherGender = driver.findElement(By.xpath("//input[@value='other']"));

        maleGender.click();
        Assert.assertTrue(maleGender.isSelected());
        Assert.assertFalse(femaleGender.isSelected());
        Assert.assertFalse(otherGender.isSelected());

        femaleGender.click();
        Assert.assertFalse(maleGender.isSelected());
        Assert.assertTrue(femaleGender.isSelected());
        Assert.assertFalse(otherGender.isSelected());

        otherGender.click();
        Assert.assertFalse(maleGender.isSelected());
        Assert.assertFalse(femaleGender.isSelected());
        Assert.assertTrue(otherGender.isSelected());

        //Date of birth
        WebElement dateOfBirth = driver.findElement(By.xpath("//input[@name='birthday']"));
        dateOfBirth.sendKeys("1/10/1990");

        //Select Department
        WebElement department = driver.findElement(By.xpath("//select[@name='department']"));
        Select selectDepartment = new Select(department);
        List<WebElement> options = selectDepartment.getOptions();
        System.out.println(options.size());
        for (WebElement option : options) {
            System.out.println("Department List = " + option.getText());
        }
        selectDepartment.selectByVisibleText("Accounting Office");
        String expected = selectDepartment.getFirstSelectedOption().getText();
        String actual = "Accounting Office";
        Assert.assertEquals(actual, expected);

        System.out.println("*************************");

        //Select job title
        WebElement jobTitle = driver.findElement(By.xpath("//select[@name='job_title']"));
        Select selectJobTitle = new Select(jobTitle);
        List<WebElement> selectJob = selectJobTitle.getOptions();
        System.out.println("selectJob.size() = " + selectJob.size());
        for (WebElement webElement : selectJob) {
            System.out.println("webElement = " + webElement.getText());
        }
        selectJobTitle.selectByIndex(3);
        expected = selectJobTitle.getFirstSelectedOption().getText();
        actual = "Developer";
        Assert.assertEquals(actual, expected);

        //Select Programming Languages
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

        //Sign Up
        WebElement signUp = driver.findElement(By.id("wooden_spoon"));
        signUp.click();
        WebElement verifyText1 = driver.findElement(By.xpath("//h4[contains(text(),'Well done!')]"));
        expected = verifyText1.getText();
        actual = "Well done!";
        Assert.assertEquals(actual, expected);
        WebElement verifyText2 = driver.findElement(By.xpath("//p[contains(text(),'success')]"));
        expected = verifyText2.getText();
        actual = "You've successfully completed registration!";
        Assert.assertEquals(actual, expected);
    }
}
