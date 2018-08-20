package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends Page {
    @FindBy(xpath = "//button[@name='remove_cart_item']")
    private WebElement removeItemButton;
    @FindBy(xpath = "//*[@class='shortcuts']/li")
    private List<WebElement> productTypes;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public CartPage removeCartItem(){
        WebElement currentDataTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='dataTable rounded-corners']")));
        removeItemButton.click();
        wait.until(ExpectedConditions.stalenessOf(currentDataTable));
        return this;
    }
    public int getItemTypeNumber(){
        wait.until(ExpectedConditions.visibilityOf(productTypes.get(0)));
        return productTypes.size();
    }
}
