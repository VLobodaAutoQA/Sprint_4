package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.EnvConfig;

public class MainPage {
    private final WebDriver driver;

    protected final By goButton = By.className("Header_Button__28dPO");
    protected final By orderInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    protected final By statusButton = By.className("Header_Link__1TAG7");

    // Переменные для второго теста
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private final By orderButtonTwo = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }

    public void typeOrderId(String orderId) {
        driver.findElement(orderInputField).sendKeys(orderId);
    }

    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }

    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);
        return this;
    }

    // Отсюда пойдет второй тест:
    // Кликаем на кнопку "Заказать" в правом верхнем углу
    public MainPage clickOnOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }

    public MainPage clickOnOrderButtonTwo() {
        driver.findElement(orderButtonTwo).click();
        return this;
    }


}