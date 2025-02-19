package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AccountPage {
    @Step("Проверить ,что успешно вошли  в аккаунт")
    public AccountPage checkAccount(String fullName){
        $("#leftPanel").shouldHave(text("Welcome " + fullName));//#=id
        return this;
    }
    @Step("Проверить ,что  аккаунт успешно создан ")
    public AccountPage checkСreateAccount(String UsernName){
        $("#rightPanel").shouldHave(text("Welcome "+UsernName));
        return this;
    }
    @Step("Проверить ,что появилось предупреждение поле формы {nameField} не заполнено")
    public AccountPage checkFieldNotFilled(String nameField){
        $("#customerForm").shouldHave(text(nameField+" is required."));
        return this;
    }
    @Step("Проверить ,что появилось предупреждение пароли не совпадают ")
    public AccountPage checkPasswordsNotMatch(){
        $("#customerForm").shouldHave(text("Passwords did not match"));
        return this;
    }
    @Step("Проверить ,что  аккаунт успешно создан ")
    public AccountPage checkLoginInformation(){
        $("#rightPanel").shouldHave(text("Your login information was located successfully. You are now logged in. "));
        return this;
    }
    @Step("Проверить , Username")
    public AccountPage checkUsername(String username){
        $("#rightPanel").shouldHave(text(username));
        return this;
    }
    @Step("Проверить , Username")
    public AccountPage checkPassword( String password){
        $("#rightPanel").shouldHave(text(password));
        return this;
    }
    @Step("Нажать на строчку Transfer Funds")
    public AccountPage clickTransferFunds() {
        $("[href='transfer.htm']").click();
        return this;
    }
    @Step("Нажать на кнопку Open New Account ")
    public AccountPage clickOpenNewAccount() {
        SelenideElement button = $("input.button");

        Selenide.executeJavaScript("arguments[0].dispatchEvent(new MouseEvent('mousedown', { bubbles: true }));", button);
        Selenide.executeJavaScript("arguments[0].dispatchEvent(new MouseEvent('mouseup', { bubbles: true }));", button);
        Selenide.executeJavaScript("arguments[0].dispatchEvent(new MouseEvent('click', { bubbles: true }));", button);

        return this;
    }
    @Step("Ввести сумму перевода ")
    public AccountPage printAmount(String amount) {
        $("#amount").setValue(amount);
        return this;
    }
    @Step("Выбрать счет")
    public AccountPage chooseAccount(String account) {
        $("#toAccountId").selectOption(1);
        return this;
    }
    @Step("нажать на копку Transfer")
    public AccountPage clickTransfer() {
        $("[value='Transfer']").click();
        return this;
    }
    @Step("Проверить, сообщение о успешном переводе")
    public AccountPage checkTransfer() {
        $("#showResult").shouldHave(text("Transfer Complete!"));
        return this;
    }


}
