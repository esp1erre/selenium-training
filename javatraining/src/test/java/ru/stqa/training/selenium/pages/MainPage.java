package ru.stqa.training.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {
    @FindBy(xpath = "//*[@id='box-most-popular']//a[1]")
    private WebElement firstProduct;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public MainPage open(){
        driver.get("http://localhost/litecart/en/");
        return this;
    }
    public void goToFirstProductPage(){
        driver.get(firstProduct.getAttribute("href"));
    }
}
