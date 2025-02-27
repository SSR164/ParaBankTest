package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {
    private SelenideElement leftPanel = $("#leftPanel"),
            rightPanel = $("#rightPanel"),
            customerForm = $("#customerForm"),
            transferFunds = $("[href='transfer.htm']"),
            amountPrint = $("#amount"),
            chooseAccount = $("#toAccountId"),
            clickTransfer = $("[value='Transfer']"),
            checkTransfer = $("#showResult");


    @Step("Проверить ,что успешно вошли  в аккаунт")
    public AccountPage checkAccount(String fullName) {
        leftPanel.shouldHave(text("Welcome " + fullName));
        return this;
    }

    @Step("Проверить ,что  аккаунт успешно создан ")
    public AccountPage checkСreateAccount(String UsernName) {
        rightPanel.shouldHave(text("Welcome " + UsernName));
        return this;
    }

    @Step("Проверить ,что появилось предупреждение поле формы {nameField} не заполнено")
    public AccountPage checkFieldNotFilled(String nameField) {
        customerForm.shouldHave(text(nameField + " is required."));
        return this;
    }

    @Step("Проверить ,что появилось предупреждение пароли не совпадают ")
    public AccountPage checkPasswordsNotMatch() {
        customerForm.shouldHave(text("Passwords did not match"));
        return this;
    }

    @Step("Проверить ,что  аккаунт успешно создан ")
    public AccountPage checkLoginInformation() {
        rightPanel.shouldHave(text("Your login information was located successfully. You are now logged in. "));
        return this;
    }

    @Step("Проверить , Username")
    public AccountPage checkUsername(String username) {
        rightPanel.shouldHave(text(username));
        return this;
    }

    @Step("Проверить , Username")
    public AccountPage checkPassword(String password) {
        rightPanel.shouldHave(text(password));
        return this;
    }

    @Step("Нажать на строчку Transfer Funds")
    public AccountPage clickTransferFunds() {
        transferFunds.click();
        return this;
    }

    @Step("Ввести сумму перевода ")
    public AccountPage printAmount(String amount) {
        amountPrint.setValue(amount);
        return this;
    }

    @Step("Выбрать счет")
    public AccountPage chooseAccount(String account) {
        chooseAccount.selectOption(1);
        return this;
    }

    @Step("нажать на копку Transfer")
    public AccountPage clickTransfer() {
        clickTransfer.click();
        return this;
    }

    @Step("Проверить, сообщение о успешном переводе")
    public AccountPage checkTransfer() {
        checkTransfer.shouldHave(text("Transfer Complete!"));
        return this;
    }


}
