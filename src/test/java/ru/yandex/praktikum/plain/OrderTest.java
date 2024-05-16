package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderFirstPage;
import ru.yandex.praktikum.page.OrderSecondPage;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private String name; //имя заказчика
    private String lastname; //фамилия заказчика
    private String adress; //адрес доставки
    private String station; //станция
    private String number; //номер заказчика
    private String date; //дата доставки
    private boolean isDisplayed; //условие для проверки

    public OrderTest(String name, String lastname, String adress, String station, String number, String date, boolean isDisplayed) {
        this.name = name;
        this.lastname = lastname;
        this.adress = adress;
        this.station = station;
        this.number = number;
        this.date = date;
        this.isDisplayed = isDisplayed;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Дмитрий", "Фамилиев", "Адрес", "Арбатская", "899921431243", "01.01.2025", true},
                {"Григорий", "Фамилов", "Адрес2", "Технопарк", "899921431244", "02.02.2025", true}

        };
    }

    @Before
    public void setup() {
        webDriver = webDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void createOrder() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.closeCookiesWindow();
        mainPage.clickcreateOrder();
        OrderFirstPage orderFirstPage = new OrderFirstPage(webDriver);
        orderFirstPage.fillFirstCustomerInfo(name, lastname, adress, station, number);
        orderFirstPage.clickNextButton();
        OrderSecondPage orderSecondPage = new OrderSecondPage(webDriver);
        orderSecondPage.fillSecondCustomerInfo(date);
        orderSecondPage.clickApproveButton();
        assertEquals(isDisplayed, orderSecondPage.checkResult());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
