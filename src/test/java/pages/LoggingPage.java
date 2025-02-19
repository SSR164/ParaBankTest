package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage {
    @Step("Открыть страницу")
    public LoginPage openPage() {
        open("/parabank/index.htm");
        return this;
    }

    @Step("Нажать на поле Username")
    public LoginPage clickUsername() {
        $("[name='username']").click();
        return this;
    }

    @Step("Заполняем  поле Username")
    public LoginPage setValueUsername(String value) {
        $("[name='username']").setValue(value);
        return this;
    }

    @Step("Заполнить  поле Password")
    public LoginPage setValuePassword(String value) {
        $("[name='password']").setValue(value);
        return this;
    }

    @Step("Нажать на кнопку LOG IN ")
    public LoginPage clickLogin() {
        $("[value='Log In']").click();
        return this;
    }

    @Step("Проверить уведомления об ошибке")
    public LoginPage checkErrorLogin() {
        $("#rightPanel").shouldHave(text("The username and password could not be verified."));
        return this;
    }
    @Step("Проверить уведомления об ошибке")
    public LoginPage checkErrorInternal() {
        $("#rightPanel").shouldHave(text("An internal error has occurred and has been logged."));
        return this;
    }


    @Step("Нажать на кнопку LOG IN ")
    public LoginPage clickRegister() {
        $("#loginPanel").$(byText("Register")).click();
        return this;
    }
    @Step("Открываем техническую страницу  Логинимся на странице ")
    public LoginPage loginPageRegisteredPerson( String sessionId){
        open("/parabank/images/bullet-hover.gif");
        getWebDriver().manage().addCookie(new Cookie("JSESSIONID",sessionId));
        return this;
    }
}