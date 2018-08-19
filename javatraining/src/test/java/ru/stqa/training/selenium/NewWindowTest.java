package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.util.Set;

public class NewWindowTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newWindowTest(){
        //logging in
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //opening countries page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //opening the first country for edit
        driver.findElement(By.xpath("//*[@title='Edit']")).click();
        String mainWindow = driver.getWindowHandle();
        //opening and closing windows one by one
        for (WebElement link : driver.findElements(By.xpath("//*[@class='fa fa-external-link']"))){
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            driver.switchTo().window(getNewWindow(driver.getWindowHandles(), mainWindow));
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public String getNewWindow(Set<String> allWindows, String mainWindow){
        assertTrue(allWindows.size() == 2);
        for (String window : allWindows){
            if (!window.equals(mainWindow)) return window;
        }
        return null;
    }
}
