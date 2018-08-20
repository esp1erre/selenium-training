package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class OneStickerTest {
    WebDriver driver;

    @Before
    public void start() {
        driver = new FirefoxDriver();
    }

    @Test
    public void testOneItemSticker() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> products = driver.findElements(By.xpath("//*[@class='listing-wrapper products']/li"));
        for (WebElement product : products) {
            int productStickerNumber = product.findElements(By.xpath(".//*[starts-with(@class, 'sticker ')]")).size();
            assertEquals(String.format("Sticker number wrong for the product:\n%s ", product.getText()),
                    1, productStickerNumber);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
