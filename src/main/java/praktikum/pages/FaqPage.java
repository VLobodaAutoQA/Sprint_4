package praktikum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class FaqPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final String questionsIdPrefix = "accordion__heading-";
    private final String answerIdPrefix = "accordion__panel-";

    public FaqPage(WebDriver driver) {
        this.driver = driver;
    }

    public FaqPage open() {
        driver.get(EnvConfig.BASE_URL);

        return this;
    }

    public FaqPage acceptCookies() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();

        return this;
    }

    private void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
    }

    private void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }

    public FaqPage clickOnQuestion(String id) {
        driver.findElement(By.id(questionsIdPrefix + id)).click();

        return this;
    }

    public FaqPage waitForAnswer(String id) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerIdPrefix + id)));

        return this;
    }

    public FaqPage checkAnswerIsInvisible(String id) {
        assert !driver.findElement(By.id(answerIdPrefix + id)).isDisplayed();

        return this;
    }

    public FaqPage checkQuestionText(String itemId, String expectedQuestion) {
        String actualQuestion = driver.findElement(By.id("accordion__heading-" + itemId)).getText();
        Assert.assertEquals("Текст вопроса не соответствует ожидаемому", expectedQuestion, actualQuestion);
        return this;
    }

    public FaqPage checkAnswerText(String itemId, String expectedText) {
        String panelId = "accordion__panel-" + itemId;
        WebElement answer = driver.findElement(By.id(panelId));
        Assert.assertEquals("Текст ответа не совпадает", expectedText, answer.getText());
        return this;
    }
}