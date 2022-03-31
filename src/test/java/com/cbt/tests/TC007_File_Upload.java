package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC007_File_Upload {

    /*
       Test case #7
        1. Go to “https://practice-cybertekschool.herokuapp.com”
        2. And click on “File Upload".
        3. Upload any file with .txt extension from your computer.
        4. Click “Upload” button.
        5. Verify that subject is: “File Uploaded!”
        6. Verify that uploaded file name is displayed.
        Note: use element.sendKeys(“/file/path”) with specifying path to the file for uploading.
        Run this method against “Choose File” button.
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
    public void FileUpload(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        WebElement chooseFile = driver.findElement(By.id("file-upload"));
        WebElement uploadBtn = driver.findElement(By.id("file-submit"));
        chooseFile.sendKeys("/Users/tdmr/Desktop/Text.rtf");
        uploadBtn.click();
        WebElement uploadedFiles = driver.findElement(By.id("uploaded-files"));
        String expectedUploadedFiles = uploadedFiles.getText();
        String actualUploadedFiles = "Text.rtf";
        Assert.assertEquals(actualUploadedFiles, expectedUploadedFiles);
        System.out.println("expectedUploadedFiles = " + expectedUploadedFiles);
    }
    @Test
    public void FileUploaded2(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        WebElement chooseFile = driver.findElement(By.id("file-upload"));
        WebElement uploadBtn = driver.findElement(By.id("file-submit"));

        String filePath = "src/test/resources/Text.txt";
        String projectPath = System.getProperty("user.dir");
        String fullPath = projectPath + "/" + filePath;
        System.out.println("fullPath = " + fullPath);

        chooseFile.sendKeys(fullPath);
        uploadBtn.click();
        WebElement uploadedFiles = driver.findElement(By.id("uploaded-files"));
        String expectedUploadedFiles = uploadedFiles.getText();
        String actualUploadedFiles = "Text.txt";
        Assert.assertEquals(actualUploadedFiles, expectedUploadedFiles);

    }
}
