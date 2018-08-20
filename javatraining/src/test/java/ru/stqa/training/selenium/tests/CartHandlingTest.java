package ru.stqa.training.selenium.tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartHandlingTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void cartHandlingTest() {
        for (int i = 0; i < 3; i++){
            driver.get("http://localhost/litecart/en/");
            driver.get(driver.findElement(By.xpath("//*[@id='box-most-popular']//a[1]")).getAttribute("href"));
            int initialProductQuantity = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='cart']//*[@class='quantity']"))).getText());
            if (driver.findElements(By.xpath("//select[@name='options[Size]']")).size() > 0){
                WebElement sizeDropdown = driver.findElement(By.xpath("//select[@name='options[Size]']"));
                sizeDropdown.click();
                sizeDropdown.findElement(By.xpath(".//option[@value='Medium']")).click();
            }
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='cart']//*[@class='quantity']"), String.valueOf(initialProductQuantity + 1)));
        }
        driver.findElement(By.partialLinkText("Checkout")).click();
        int itemTypeNumber = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@class='shortcuts']/li"), 0)).size();
        for (int i = 0; i < itemTypeNumber; i++){
            WebElement dataTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='dataTable rounded-corners']")));
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(dataTable));
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}
