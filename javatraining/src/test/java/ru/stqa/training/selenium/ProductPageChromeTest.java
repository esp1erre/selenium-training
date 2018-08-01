package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class ProductPageChromeTest {
    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void productPageChromeTest(){
        driver.get("http://localhost/litecart/en/");
        WebElement mainPageProduct = driver.findElement(By.xpath("//*[@id='box-campaigns']//a[@class='link']"));
        String mainPageName = mainPageProduct.findElement(By.xpath("./div[@class='name']")).getText();
        WebElement regPrice = mainPageProduct.findElement(By.xpath(".//*[@class='regular-price']"));
        assertTrue(regPrice.getTagName().equals("s"));
        String color = regPrice.getCssValue("color");
        assertTrue(getChannel(color, RGB.RED) == getChannel(color, RGB.GREEN) && getChannel(color, RGB.RED) == getChannel(color, RGB.BLUE));
        String mainPageRegPriceText = regPrice.getText();
        WebElement campPrice = mainPageProduct.findElement(By.xpath(".//*[@class='campaign-price']"));
        assertTrue(campPrice.getTagName().equals("strong"));
        color = campPrice.getCssValue("color");
        assertTrue(getChannel(color, RGB.GREEN) == 0 && getChannel(color, RGB.BLUE) == 0);
        String mainPageCampPriceText = campPrice.getText();
        assertTrue(campPrice.getSize().height > regPrice.getSize().height && campPrice.getSize().width > regPrice.getSize().width);
        driver.get(mainPageProduct.getAttribute("href"));
        assertTrue(driver.findElement(By.xpath("//h1")).getText().equals(mainPageName));
        regPrice = driver.findElement(By.xpath("//*[@class='regular-price']"));
        assertTrue(regPrice.getText().equals(mainPageRegPriceText));
        assertTrue(regPrice.getTagName().equals("s"));
        color = regPrice.getCssValue("color");
        assertTrue(getChannel(color, RGB.RED) == getChannel(color, RGB.GREEN) && getChannel(color, RGB.RED) == getChannel(color, RGB.BLUE));
        campPrice = driver.findElement(By.xpath("//*[@class='campaign-price']"));
        assertTrue(campPrice.getText().equals(mainPageCampPriceText));
        assertTrue(campPrice.getTagName().equals("strong"));
        color = campPrice.getCssValue("color");
        assertTrue(getChannel(color, RGB.GREEN) == 0 && getChannel(color, RGB.BLUE) == 0);
        assertTrue(campPrice.getSize().height > regPrice.getSize().height && campPrice.getSize().width > regPrice.getSize().width);
    }

    @After
    public void stop(){
        driver.quit();
    }

    public int getChannel(String rgbString, RGB channel){
        rgbString = rgbString.substring(5, rgbString.length() - 1);
        String[] channelStrings = rgbString.split(", ");
        switch (channel){
            case RED: return Integer.parseInt(channelStrings[0]);
            case GREEN: return Integer.parseInt(channelStrings[1]);
            case BLUE: return Integer.parseInt(channelStrings[2]);
            default: return -1;
        }
    }

    public enum RGB {RED, GREEN, BLUE}
}