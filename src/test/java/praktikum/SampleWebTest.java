package praktikum;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class SampleWebTest {
    // junit5 - @RegisterExtension (@Rule)
    @Rule
    public DriverRule factory = new DriverRule();

    // На примере выше браузер будет открываться и закрываться на каждом тест.
    // На примере ниже браузер откроется один раз и закроется после выполнения всех тестов! Понадобится для блока ВОПРОС-ОТВЕТ, чтобы прокликать каждый элемент
    // @ClassRule
    // public static DriverRule factory = new DriverRule();

    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();

        var mainPage = new MainPage(driver);

        mainPage.openMainPage();

        //Клик на кнопку "Статус заказа"
        mainPage.clickOnStatus();

        // Вводим в поле "Введите номер заказа" номер несуществующего заказа
        String invalidId = "11111111";
        mainPage.typeOrderId(invalidId);

        // Кликаем на кнопку "Go!"
        var statusPage = mainPage.clickOnGo();

        //Так как в интерфейсе используется анимация, поэтому нам надо дождаться пока она прогрузится, иначе тесты упадут. Поэтому используем Wait
        statusPage.checkNotFoundMessage();
    }
}
