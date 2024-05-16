package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderFirstPage {
    private final WebDriver webDriver;
    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']");
    private final By lastnameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayInputLocator = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final String stationLocator = "//div[text()='%s']";
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']");

    public OrderFirstPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillFirstCustomerInfo(String name, String lastname, String address, String subwayTitle, String phone) {


        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);


        WebElement lastnameInput = webDriver.findElement(lastnameInputLocator);
        lastnameInput.sendKeys(lastname);


        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);


        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();

        WebElement subwayStation = webDriver.findElement(By.xpath(String.format(stationLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", subwayStation);
        subwayStation.click();


        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);
    }

    public void clickNextButton() {
        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }
}
