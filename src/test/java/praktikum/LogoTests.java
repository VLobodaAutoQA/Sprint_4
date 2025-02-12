package praktikum;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.FaqPage;
import praktikum.pages.MainPage;

import java.time.Duration;

public class LogoTests {
    private final String INVALID_ORDER_NUMBER = "123";

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        new FaqPage(driverRule.getDriver()).acceptCookies();
    }

    @Test
    public void clickOnLogo() {
        WebDriver driver = driverRule.getDriver();

        MainPage main = new MainPage(driver)
                .open();

        var currentWindow = driver.getWindowHandle();

        driver.findElement(By.className("Header_LogoYandex__3TSOI")).click();

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        String newWindow = driver.getWindowHandles().stream()
                .filter(w -> !w.equals(currentWindow))
                .findAny().get();
        driver.switchTo().window(newWindow);

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[name]")));

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name]")));
        driver.findElement(By.name("text")).sendKeys("hello world");
    }
}