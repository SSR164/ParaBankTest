package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {
    @Step("Проверить ,что успешно вошли  в аккаунт")
    public AccountPage checkAccount(){
        $("#leftPanel").shouldHave(text("Welcome"));//#=id
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
}
