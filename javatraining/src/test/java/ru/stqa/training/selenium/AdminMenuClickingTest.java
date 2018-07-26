package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminMenuClickingTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void clickingTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        int menuSize = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li")).size();
        for (int i = 1; i <= menuSize; i++) {
            driver.findElement(By.xpath(String.format("//*[@id='box-apps-menu']/li[%s]", i))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
            int subMenuSize = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li[@class='selected']//li")).size();
            for (int j = 1; j <= subMenuSize; j++) {
                driver.findElement(By.xpath(String.format("//*[@id='box-apps-menu']/li[@class='selected']//li[%s]", j))).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
