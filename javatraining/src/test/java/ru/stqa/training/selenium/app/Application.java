package ru.stqa.training.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.pages.CartPage;
import ru.stqa.training.selenium.pages.MainPage;
import ru.stqa.training.selenium.pages.ProductPage;

public class Application {
    public WebDriver driver;
    public WebDriverWait wait;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver, wait);
        productPage = new ProductPage(driver, wait);
        cartPage = new CartPage(driver, wait);
    }

    public void quit(){
        driver.quit();
    }

    public void addFirstProductsToCart(int countTimes){
        for (int i = 0; i < countTimes; i++){
            mainPage.open().goToFirstProductPage();
            productPage.setSizeIfApplicable("Medium").addToCart();
        }
    }

    public void clearCart(){
        int typeNumber = cartPage.getItemTypeNumber();
        for (int i = 0; i < typeNumber; i++){
            cartPage.removeCartItem();
        }
    }
    public void checkout(){
        productPage.checkout();
    }
}
