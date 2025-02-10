package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.FaqPage;
import praktikum.pages.MainPage;
import praktikum.pages.RentPage;
import praktikum.pages.WhoIsTheScooterPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static praktikum.FaqTest.driverRule;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private MainPage mainPage;
    private final String name; // Имя арендатора
    private final String lastName; // Фамилия арендатора
    private final String address; // Адрес арендатора
    private final int stateMetroNumber; // Номер станции метро
    private final String telephoneNumber; // Телефон арендатора
    private final String date; // Дата аренды
    private final String duration; // Длительность аренды
    private final String colour; // Цвет самоката
    private final String comment; // Комментарий от пользователя
    private final String expectedHeader = "Заказ оформлен"; // Ожидаемый заголовок после оформления заказа


    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    public CreateOrderTest(String name, String lastName, String address, int stateMetroNumber, String telephoneNumber,
                           String date, String duration, String colour, String comment) {
        this.name = name; // Вводим имя
        this.lastName = lastName; // Вводим фамилию
        this.address = address; // Вводим адрес
        this.stateMetroNumber = stateMetroNumber; // Выбираем номер метро
        this.telephoneNumber = telephoneNumber; // Вводим номер телефона
        this.date = date; // Выбираем дату
        this.duration = duration; // Выбираем длительность аренды
        this.colour = colour; // Выбираем цвет самоката
        this.comment = comment; // Вводим комментарий
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Тоби", "Магуайр", "улица Ленина", 4, "89991111111", "09.02.2025", "сутки", "BLACK", "Тест один"},
                {"Джейсен", "Стетхэм", "улица Квартальная", 6, "89992222222", "09.02.2025", "двое суток", "GREY", "Тест два"},
                {"Брюс", "Ли", "улица Демократическая", 9, "89993333333", "10.02.2025", "трое суток", "BLACK", "Тест три"},
                {"Джейсен", "Стетхэм", "улица Ленина", 4, "89991111111", "10.02.2025", "четверо суток", "GREY", "Тест четыре"},
                {"Тоби", "Магуайр", "улица Квартальная", 6, "89992222222", "11.02.2025", "пятеро суток", "BLACK", "Тест пять"},
                {"Брюс", "Ли", "улица Демократическая", 9, "89993333333", "11.02.2025", "шестеро суток", "GREY", "Тест шесть"},
                {"Тоби", "Магуайр", "улица Ленина", 4, "89991111111", "09.02.2025", "семеро суток", "BLACK", "Тест семь"},
        };
    }

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
       // new FaqPage(driverRule.getDriver()).acceptCookies();
    }

    @Test
    public void createOrder() throws Exception {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        var mainPage = new MainPage(driver);

        mainPage.openMainPage();
        mainPage.clickOnOrderButton();

        new WhoIsTheScooterPage(driverRule.getDriver())
                .waitForLoadOrderPage()
                .inputName(name)
                .inputLastName(lastName)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        RentPage rentPage = new RentPage(driverRule.getDriver());
        // RentPage rentPage = new RentPage(driver);
        // new RentPage(driverRule.getDriver())
        rentPage.waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

                // rentPage.clickButtonYes();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']"))); // Ожидание кликабельности поля
        driver.findElement(By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")).click();


        // RentPage rentPage = new RentPage(driverRule.getDriver());
        // rentPage.clickButtonYes();
        assertTrue(rentPage.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}
