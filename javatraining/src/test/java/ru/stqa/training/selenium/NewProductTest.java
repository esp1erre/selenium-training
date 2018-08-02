package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Random;

public class NewProductTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addNewProductTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Catalog")));
        driver.findElement(By.linkText("Catalog")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Add New Product")));
        driver.findElement(By.partialLinkText("Add New Product")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='radio' and @value='1']")));
        //filling general tab
        driver.findElement(By.xpath("//input[@type='radio' and @value='1']")).click();
        String number = String.valueOf(new Random().nextInt(90000) + 10000);
        String name = "Ashen Duck No" + number;
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("ashen" + number);
        driver.findElement(By.xpath("//input[@name='categories[]' and @data-name='Root']")).click();
        driver.findElement(By.xpath("//input[@name='categories[]' and @data-name='Rubber Ducks']")).click();
        WebElement categoryDropdown = driver.findElement(By.xpath("//select[@name='default_category_id']"));
        categoryDropdown.click();
        categoryDropdown.findElement(By.xpath(".//option[@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='product_groups[]' and @value='1-3']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).clear();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("1");
        ClassLoader classLoader = getClass().getClassLoader();
        File pic = new File(classLoader.getResource("duck_pic.png").getFile());
        String absolutePath = pic.getAbsolutePath();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(absolutePath);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('value', '2001-01-01')", driver.findElement(By.xpath("//input[@name='date_valid_from']")));
        executor.executeScript("arguments[0].setAttribute('value', '2018-12-12')", driver.findElement(By.xpath("//input[@name='date_valid_to']")));
        driver.findElement(By.linkText("Information")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='manufacturer_id']")));
        //filling information tab
        WebElement manufacturerDropdown = driver.findElement(By.xpath("//select[@name='manufacturer_id']"));
        manufacturerDropdown.click();
        manufacturerDropdown.findElement(By.xpath(".//option[@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("duck, sorrow, eternity");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Tranquil bath warden");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("Embrace the merciful slumber with your new all-forgiving friend");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("Ashen Duck");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("Guardian of peace");
        driver.findElement(By.linkText("Prices")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='purchase_price']")));
        //filling prices tab
        driver.findElement(By.xpath("//input[@name='purchase_price']")).clear();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("100");
        driver.findElement(By.xpath("//input[@name='gross_prices[USD]']")).clear();
        driver.findElement(By.xpath("//input[@name='gross_prices[USD]']")).sendKeys("100");
        driver.findElement(By.xpath("//input[@name='gross_prices[EUR]']")).clear();
        driver.findElement(By.xpath("//input[@name='gross_prices[EUR]']")).sendKeys("70");
        driver.findElement(By.xpath("//button[@name='save']")).click();
        //checking added product
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(name)));
    }

    @After
    public void stop() {
        driver.quit();
    }
}
