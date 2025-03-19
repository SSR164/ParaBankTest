package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement firstName = $("[name='customer.firstName']");
    private final SelenideElement lastName = $("[name='customer.lastName']");
    private final SelenideElement address = $("[name='customer.address.street']");
    private final SelenideElement city = $("[name='customer.address.city']");
    private final SelenideElement state = $("[name='customer.address.state']");
    private final SelenideElement zipCode = $("[name='customer.address.zipCode']");
    private final SelenideElement phoneNumber = $("[name='customer.phoneNumber']");
    private final SelenideElement ssn = $("[name='customer.ssn']");
    private final SelenideElement username = $("[name='customer.username']");
    private final SelenideElement password = $("[name='customer.password']");
    private final SelenideElement confirm = $("[name='repeatedPassword']");
    private final SelenideElement register = $("#customerForm").$(".button");
    private final SelenideElement forgotLoginInfo = $("#loginPanel").$(byText("Forgot login info?"));

    @Step("Заполнить поле FirstName")//
    public RegisterPage setValueFirstName(String firstNameCustomer) {
        firstName.setValue(firstNameCustomer);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public pages.RegisterPage setValueLastName(String lastNameCustomer) {
        lastName.setValue(lastNameCustomer);
        return this;
    }

    @Step("Заполнить поле Address")
    public pages.RegisterPage setValueAddress(String addressCustomer) {
        address.setValue(addressCustomer);
        return this;
    }

    @Step("Заполнить поле City")
    public pages.RegisterPage setValueCity(String cityCustomer) {
        city.setValue(cityCustomer);
        return this;
    }

    @Step("Заполнить поле State")
    public pages.RegisterPage setValueState(String stateCustomer) {
        state.setValue(stateCustomer);
        return this;
    }

    @Step("Заполнить поле Zip Code")
    public pages.RegisterPage setValueZipCode(String zipCodeCustomer) {
        zipCode.setValue(zipCodeCustomer);
        return this;
    }

    @Step("Заполнить поле Phone")
    public pages.RegisterPage setValuePhone(String phoneCustomer) {
        phoneNumber.setValue(phoneCustomer);
        return this;
    }

    @Step("Заполнить поле SSN")
    public pages.RegisterPage setValueSSN(String ssnCustomer) {
        ssn.setValue(ssnCustomer);
        return this;
    }

    @Step("Заполнить поле Username")
    public pages.RegisterPage setValueUserName(String userNameCustomer) {
        username.setValue(userNameCustomer);
        return this;
    }

    @Step("Заполнить поле Password")
    public pages.RegisterPage setValuePassword(String passwordCustomer) {
        password.setValue(passwordCustomer);
        return this;
    }

    @Step("Заполнить поле Confirm")
    public pages.RegisterPage setValueConfirm(String passwordCustomer) {
        confirm.setValue(passwordCustomer);
        return this;
    }

    @Step("Нажать на кнопку Register")
    public pages.RegisterPage clickRegister() {
        register.click();
        return this;
    }

    @Step("Нажать на кнопку Forgot login info?")
    public pages.RegisterPage clickForgotLoginInfo() {
        forgotLoginInfo.click();
        return this;
    }

}

