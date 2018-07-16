package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest(){
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Hello world!");
        driver.findElement(By.name("btnK")).click();
        wait.until(ExpectedConditions.titleIs("Hello world! - Google Search"));
        //wait.until(ExpectedConditions.titleIs("Hello world! - Поиск в Google"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
