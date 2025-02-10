package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;

    protected final By goButton = By.className("Header_Button__28dPO");
    protected final By orderInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    protected final By statusButton = By.className("Header_Link__1TAG7");
    private final By orderStatusField = By.className("Header_Link__1TAG7");
    private final By orderField = By.cssSelector(".Input_Input__1iN_Z");

    // Переменные для второго теста
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");


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

    public MainPage enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderField));

        driver.findElement(orderField).sendKeys(orderNumber);

        return this;
    }

    public MainPage clickOnOrderStatus() {
        driver.findElement(orderStatusField).click();

        return this;
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



}