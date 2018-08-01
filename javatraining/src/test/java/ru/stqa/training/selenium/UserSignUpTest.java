package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class UserSignUpTest {
    WebDriver driver;

    @Before
    public void start() {
        driver = new FirefoxDriver();
    }

    @Test
    public void userSignUpTest() {
        driver.get("http://localhost/litecart/en/");
        //filling input fields
        driver.findElement(By.xpath("//*[@id='box-account-login']//a")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Homer");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Simpson");
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("742 Evergreen Terrace");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("58008");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Springfield");
        //selecting country
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement countryDropdown = driver.findElement(By.xpath("//*[@name='country_code']"));
        executor.executeScript("arguments[0].selectedIndex = 224; arguments[0].dispatchEvent(new Event('change'))", countryDropdown);
        //selecting state
        WebElement zoneDropdown = driver.findElement(By.xpath("//select[@name='zone_code']"));
        zoneDropdown.click();
        zoneDropdown.click();
        zoneDropdown.findElement(By.xpath(".//option[@value='ND']")).click();
        //entering email
        String email = "homer" + String.valueOf(new Random().nextInt(90000) + 10000) + "@gmail.com";
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        //filling the remaining input fields
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("+12345678901");
        String password = "maggie";
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        //submitting
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //logging out
        driver.findElement(By.linkText("Logout")).click();
        //logging in with the same user
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        //logging out
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void stop() {
        driver.quit();
    }
}
