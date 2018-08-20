package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LogAbsenceTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testLogAbsence(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        int productAmount = driver.findElements(By.cssSelector("a[href*='product_id'][title='Edit']")).size();
        for (int i = 0; i < productAmount; i++){
            driver.findElements(By.cssSelector("a[href*='product_id'][title='Edit']")).get(i).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".content")));
            assertEquals(0, driver.manage().logs().get("browser").getAll().size());
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}
