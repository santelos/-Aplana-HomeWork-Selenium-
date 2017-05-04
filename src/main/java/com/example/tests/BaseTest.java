package com.example.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BaseTest {
    private static WebDriver driver;
    private String baseUrl;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        driver = new FirefoxDriver();
//        baseUrl = System.getProperty("app.url");
        baseUrl = "https://ipizza.ru";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("div.subCont")).click();
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    public WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void waitUntilElementClickable(String xpath) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }


    public void fillField(String field, String value) {
        driver.findElement(By.xpath(field)).click();
        driver.findElement(By.xpath(field)).clear();
        driver.findElement(By.xpath(field)).sendKeys(value);
    }

    public void checkFillField(String field, String expectedValue) {
        String actualValue = driver.findElement(By.xpath(field)).getAttribute("value");
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);
    }
}