package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends Page {

    @FindBy(xpath = "//select[@name='options[Size]']")
    private List<WebElement> sizeDropdown;
    @FindBy(xpath = "//button[@name='add_cart_product']")
    private WebElement addToCartButton;
    @FindBy(partialLinkText = "Checkout")
    private WebElement checkoutButton;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public ProductPage setSizeIfApplicable(String size){
        if (!sizeDropdown.isEmpty()){
            sizeDropdown.get(0).click();
            sizeDropdown.get(0).findElement(By.xpath(String.format(".//option[@value='%s']", size))).click();
        }
        return this;
    }
    public ProductPage addToCart(){
        int initialProductQuantity = Integer.parseInt(wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[@id='cart']//*[@class='quantity']"))).getText());
        addToCartButton.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='cart']//*[@class='quantity']"),
                String.valueOf(initialProductQuantity + 1)));
        return this;
    }
    public void checkout(){
        checkoutButton.click();
    }
}
