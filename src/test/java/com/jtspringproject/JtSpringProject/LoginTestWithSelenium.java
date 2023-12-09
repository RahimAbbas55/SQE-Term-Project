package com.jtspringproject.JtSpringProject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;


public class LoginTestWithSelenium {

    private WebDriver driver;

    @Test
    public void testLogin() {

        System.setProperty("webdriver.chrome.driver", "E:\\ForRahim\\SQE-Term-Project\\src\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        // Enter username and password
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("lisa");
        passwordInput.sendKeys("765");
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        String pageContent = driver.getPageSource();
        assertTrue("Search", pageContent.contains("Search"));

        if (driver != null) {
            driver.quit();
        }

    }

}

