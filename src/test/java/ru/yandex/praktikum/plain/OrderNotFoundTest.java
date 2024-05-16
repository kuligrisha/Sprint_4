package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;

import static junit.framework.TestCase.assertTrue;

public class OrderNotFoundTest {
    private WebDriver webDriver;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();

    @Before
    public void setup() {
        webDriver = webDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderNotFound() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderStatusButton("12345678901");
        mainPage.clickGoButton();
        assertTrue(mainPage.notFoundImageIsDisplayed());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
