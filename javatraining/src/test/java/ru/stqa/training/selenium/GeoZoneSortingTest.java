package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GeoZoneSortingTest {
    WebDriver driver;

    @Before
    public void start() {
        driver = new FirefoxDriver();
    }

    @Test
    public void geoZoneSortTest(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<String> countryLinks = new ArrayList<String>();
        for (WebElement countryRow : driver.findElements(By.xpath("//*[@class='row']"))){
            countryLinks.add(countryRow.findElement(By.xpath("./td[3]/a")).getAttribute("href"));
        }
        for (String countryLink : countryLinks){
            driver.get(countryLink);
            List<String> zoneNames = new ArrayList<String>();
            for (WebElement selectedZone : driver.findElements(By.xpath("//*[@class='dataTable']//tr/td[3]//*[@selected]"))){
                zoneNames.add(selectedZone.getText());
            }
            for (int i = 1; i < zoneNames.size(); i++){
                assertTrue(String.format("Zones %s and %s are mixed up!", zoneNames.get(i-1), zoneNames.get(i)), zoneNames.get(i).compareTo(zoneNames.get(i-1)) >= 0);
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
    }
}
