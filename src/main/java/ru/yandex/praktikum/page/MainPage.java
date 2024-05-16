package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver webDriver;
    private By orderStatusLocator = By.xpath("//button[text()='Статус заказа']");
    private By cookiesButtonLocator = By.id("rcc-confirm-button");

    private By orderNumberInputLocator = By.xpath("//input[@placeholder='Введите номер заказа']");
    private By GoButtonLocator = By.xpath("//button[text()='Go!']");
    private By notFoundImageLocator = By.xpath("//img[@alt='Not found']");
    private By createOrderButtonLocator = By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']");
    private final String questionLocator = "accordion__heading-%s";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOrderStatusButton() {
        //кнопка статус заказа
        WebElement orderStatusButton = webDriver.findElement(orderStatusLocator);
        orderStatusButton.click();
    }

    public void enterOrderStatusButton(String orderNumber) {
        //кнопка ввода номера заказа
        WebElement orderInput = webDriver.findElement(orderNumberInputLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderInput));
        // ввести рандомный текст
        orderInput.sendKeys(orderNumber);
    }

    public void clickGoButton() {
        //кнопка го
        WebElement goButton = webDriver.findElement(GoButtonLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(goButton));
        goButton.click();
    }

    public boolean notFoundImageIsDisplayed() {
        // такого заказа нет
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImageLocator));
        return webDriver.findElement(notFoundImageLocator).isDisplayed();
    }

    public void clickcreateOrder() {
        WebElement createOrderButton = webDriver.findElement(createOrderButtonLocator);
        createOrderButton.click();
    }

    public void closeCookiesWindow() {
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
