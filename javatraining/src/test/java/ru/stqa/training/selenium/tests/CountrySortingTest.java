package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class CountrySortingTest {
    WebDriver driver;

    @Before
    public void start() {
        driver = new FirefoxDriver();
    }

    @Test
    public void countrySortTest(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = driver.findElements(By.xpath("//*[@class='row']"));
        List<String> multiZoneCountryLinks = new ArrayList<String>();
        for (int i = 0; i < countries.size(); i++){
            //gathering links for countries with multiple zones
            if (!countries.get(i).findElement(By.xpath(".//td[6]")).getText().equals("0")){
                multiZoneCountryLinks.add(countries.get(i).findElement(By.xpath(".//td[5]/a")).getAttribute("href"));
            }
            //checking the country sorting
            if (i > 0){
                String countryNameCurrent = countries.get(i).findElement(By.xpath(".//td[5]/a")).getText();
                String countryNamePrevious = countries.get(i-1).findElement(By.xpath(".//td[5]/a")).getText();
                assertTrue(String.format("Countries %s and %s are mixed up!", countryNamePrevious, countryNameCurrent), countryNameCurrent.compareTo(countryNamePrevious) >= 0);
            }
        }
        //checking the country zone sorting
        for (String multiZoneCountryLink : multiZoneCountryLinks){
            driver.get(multiZoneCountryLink);
            List<WebElement> countryZones = driver.findElements(By.xpath("//*[@class='dataTable']//tr/td[3]"));
            for (int i = 1; i < countryZones.size() - 1; i++){
                String zoneNameCurrent = countryZones.get(i).getText();
                String zoneNamePrevious = countryZones.get(i-1).getText();
                assertTrue(String.format("Zones %s and %s are mixed up!", zoneNamePrevious, zoneNameCurrent), zoneNameCurrent.compareTo(zoneNamePrevious) >= 0);
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
    }
}
