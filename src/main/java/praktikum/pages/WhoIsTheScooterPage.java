package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhoIsTheScooterPage {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor; // Добавил переменную для выполнения JavaScript

    // Конструктор
    public WhoIsTheScooterPage(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver; // Исправил: теперь создается jsExecutor для работы с JavaScript
    }

    private final By orderHeader = By.className("Order_Header__BZXOb");
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stateMetro = By.className("select-search__input");
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final String nameStateMetro = ".//button[@value='%s']"; // Форматная строка для выбора станции метро

    // Метод ожидания загрузки страницы заказа
    public WhoIsTheScooterPage waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(orderHeader).getText() != null &&
                        !driver.findElement(orderHeader).getText().isEmpty()
        );
        return this;
    }

    public WhoIsTheScooterPage inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }

    public WhoIsTheScooterPage inputLastName(String newLastName) {
        driver.findElement(lastName).sendKeys(newLastName);
        return this;
    }

    public WhoIsTheScooterPage inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    public WhoIsTheScooterPage changeStateMetro(int stateName) {
        driver.findElement(stateMetro).click(); // Клик по полю выбора станции метро
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateName)); // Формируем XPath для конкретной станции
        jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro)); // Прокрутка к элементу
        driver.findElement(newStateMetro).click(); // Выбор станции метро
        return this;
    }

    public WhoIsTheScooterPage inputTelephone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(telephone)); // Ожидание кликабельности поля
        driver.findElement(telephone).sendKeys(newTelephone);
        return this;
    }

    public void clickNextButton() {
        driver.findElement(buttonNext).click(); // Клик по кнопке "Далее"
    }
}
