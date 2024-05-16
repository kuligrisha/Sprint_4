package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderSecondPage {
    private final WebDriver webDriver;
    private final By dateInputLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentPeriodInputLocator = By.xpath("//div[text()='* Срок аренды']");
    private final By rentPeriodMenuItemLocator = By.xpath("//div[text()='трое суток']");
    private final By createButtonLocator = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");
    private final By approveButtonLocator = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Да']");
    private final By expectedResult = By.xpath("//div[contains(@class, 'Order_Modal__YZ-d3')]//div[text()='Заказ оформлен']");


    public OrderSecondPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillSecondCustomerInfo(String date) {
        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.sendKeys(date, Keys.ENTER);

        WebElement rentPeriodInput = webDriver.findElement(rentPeriodInputLocator);
        rentPeriodInput.click();

        WebElement rentPeriodMenuItem = webDriver.findElement(rentPeriodMenuItemLocator);
        rentPeriodMenuItem.click();

        WebElement createButton = webDriver.findElement(createButtonLocator);
        createButton.click();
    }
    public void clickApproveButton() {
        WebElement approveButton = webDriver.findElement(approveButtonLocator);
        approveButton.click();
    }
    public boolean checkResult() {
        boolean expectedResultIsDisplayed = webDriver.findElement(expectedResult).isDisplayed();
        return expectedResultIsDisplayed;
    }

}
