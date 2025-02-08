package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoggingPage {
    @Step("Открыть страницу")
    public LoggingPage openPage() {
        open("/parabank/index.htm");
        return this;
    }

    @Step("Нажать на поле Username")
    public LoggingPage clickUsername() {
        $("[name='username']").click();
        return this;
    }

    @Step("Заполняем  поле Username")
    public LoggingPage setValueUsername(String value) {
        $("[name='username']").setValue(value);
        return this;
    }

    @Step("Заполнить  поле Password")
    public LoggingPage setValuePassword(String value) {
        $("[name='password']").setValue(value);
        return this;
    }

    @Step("Нажать на кнопку LOG IN ")
    public LoggingPage clickLogin() {
        $("[value='Log In']").click();
        return this;
    }

    @Step("Проверить уведомления об ошибке")
    public LoggingPage checkErrorLogin() {
        $("#rightPanel").shouldHave(text("The username and password could not be verified."));
        return this;
    }
    @Step("Проверить уведомления об ошибке")
    public LoggingPage checkErrorInternal() {
        $("#rightPanel").shouldHave(text("An internal error has occurred and has been logged."));
        return this;
    }


    @Step("Нажать на кнопку LOG IN ")
    public LoggingPage clickRegister() {
        $("#loginPanel").$(byText("Register")).click();
        return this;
    }
}