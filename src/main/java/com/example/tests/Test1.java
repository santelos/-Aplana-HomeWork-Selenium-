package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Test1 extends BaseTest{

    @Test
    public void test1() throws Exception {
        String inputFromTable = "//*[@id='%s']";
//        driver.get(baseUrl + "/");
//        driver.findElement(By.cssSelector("div.subCont")).click();
        waitUntilElementClickable("//a[text()='МОРСКАЯ']");
        getElement("//a[text()='МОРСКАЯ']").click();
        assertEquals("Пицца не морская", true,
            getElement("//h1[text()='МОРСКАЯ']").isEnabled());

        waitUntilElementClickable("//div[@class='tocart']");
        getElement("//div[@class='tocart']").click();
        int cost = Integer.parseInt(getElement("//div[@class='centerBtn btn left redshdw bDetec']/b").getText().split(" ")[0]);

        waitUntilElementClickable("//div[text()='КОРЗИНА']");
        getElement("//div[text()='КОРЗИНА']").click();
        assertEquals("Не 'Вы заказали:'", true,
            getElement("//td[@class='maybeAds']").getText().equals("Вы заказали:"));
        assertEquals("Итоо != заказанной", cost,
            Integer.parseInt(getElement("//span[@id='fullSum']").getText()));

        waitUntilElementClickable("//a[@class='contBtn']");
        getElement("//a[@class='contBtn']").click();
        assertEquals("Не 'Информация о доставке'", true,
            getElement("//div[@class='mainForm']/div/h2").getText().equals("ИНФОРМАЦИЯ О ДОСТАВКЕ"));

//        System.out.println(String.format(inputFromTable,"customerName"));

        fillField(String.format(inputFromTable, "custumerName"),"Петр");
        fillField(String.format(inputFromTable, "custumerPhone"),"");
        fillField(String.format(inputFromTable, "custumerStreet"),"НАГАТИНСКПАЯ ПОЙМА");
        fillField(String.format(inputFromTable, "custumerHome"),"1");
        fillField(String.format(inputFromTable, "custumerPorch"),"1");
        fillField(String.format(inputFromTable, "custumerFloor"),"19");
        fillField(String.format(inputFromTable, "custumerNumber"),"195");


        checkFillField(String.format(inputFromTable, "custumerName"),"Петр");
        checkFillField(String.format(inputFromTable, "custumerPhone"),"");
        checkFillField(String.format(inputFromTable, "custumerStreet"),"НАГАТИНСКПАЯ ПОЙМА");
        checkFillField(String.format(inputFromTable, "custumerHome"),"1");
        checkFillField(String.format(inputFromTable, "custumerPorch"),"1");
        checkFillField(String.format(inputFromTable, "custumerFloor"),"19");
        checkFillField(String.format(inputFromTable, "custumerNumber"),"195");
    }
}
