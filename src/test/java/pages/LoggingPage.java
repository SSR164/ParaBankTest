package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoggingPage {
    private SelenideElement username = $("[name='username']"),
            password = $("[name='password']"),
            login = $("[value='Log In']"),
            rightPanel = $("#rightPanel"),
            loginPanel = $("#loginPanel");


    @Step("Открыть страницу")
    public LoggingPage openPage() {
        open("/parabank/index.htm");
        return this;
    }


    @Step("Заполняем  поле Username")
    public LoggingPage setValueUsername(String value) {
        username.setValue(value);
        return this;
    }

    @Step("Заполнить  поле Password")
    public LoggingPage setValuePassword(String value) {
        password.setValue(value);
        return this;
    }

    @Step("Нажать на кнопку LOG IN ")
    public LoggingPage clickLogin() {
        login.click();
        return this;
    }

    @Step("Проверить уведомления об ошибке")
    public LoggingPage checkErrorLogin() {
        rightPanel.shouldHave(text("The username and password could not be verified."));
        return this;
    }


    @Step("Нажать на кнопку LOG IN ")
    public LoggingPage clickRegister() {
        loginPanel.$(byText("Register")).click();
        return this;
    }

    @Step("Открываем техническую страницу  Логинимся на странице ")
    public LoggingPage loginPageRegisteredPerson(String sessionId) {
        open("/parabank/images/bullet-hover.gif");
        getWebDriver().manage().addCookie(new Cookie("JSESSIONID", sessionId));
        return this;
    }
}