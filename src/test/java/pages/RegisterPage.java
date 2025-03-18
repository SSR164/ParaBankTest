package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private SelenideElement firstName = $("[name='customer.firstName']"),
            lastName = $("[name='customer.lastName']"),
            address = $("[name='customer.address.street']"),
            city = $("[name='customer.address.city']"),
            state = $("[name='customer.address.state']"),
            zipCode = $("[name='customer.address.zipCode']"),
            phoneNumber = $("[name='customer.phoneNumber']"),
            ssn = $("[name='customer.ssn']"),
            username = $("[name='customer.username']"),
            password = $("[name='customer.password']"),
            confirm = $("[name='repeatedPassword']"),
            register = $("#customerForm").$(".button"),
            forgotLoginInfo = $("#loginPanel").$(byText("Forgot login info?"));

    @Step("Заполнить поле FirstName")//
    public RegisterPage setValueFirstName(String firstNameСustomer) {
        firstName.setValue(firstNameСustomer);
        return this;
    }

    @Step("Заполнить поле Last Name")//
    public pages.RegisterPage setValueLastName(String lastNameСustomer) {
        lastName.setValue(lastNameСustomer);
        return this;
    }

    @Step("Заполнить поле Address")//
    public pages.RegisterPage setValueAddress(String addressСustomer) {
        address.setValue(addressСustomer);
        return this;
    }

    @Step("Заполнить поле City")//
    public pages.RegisterPage setValueCity(String cityСustomer) {
        city.setValue(cityСustomer);
        return this;
    }

    @Step("Заполнить поле State")
    public pages.RegisterPage setValueState(String stateСustomer) {
        state.setValue(stateСustomer);
        return this;
    }

    @Step("Заполнить поле Zip Code")
    public pages.RegisterPage setValueZipCode(String zipCodeСustomer) {
        zipCode.setValue(zipCodeСustomer);
        return this;
    }

    @Step("Заполнить поле Phone")
    public pages.RegisterPage setValuePhone(String phoneСustomer) {
        phoneNumber.setValue(phoneСustomer);
        return this;
    }

    @Step("Заполнить поле SSN")
    public pages.RegisterPage setValueSSN(String ssnСustomer) {
        ssn.setValue(ssnСustomer);
        return this;
    }

    @Step("Заполнить поле Username")
    public pages.RegisterPage setValueUsernName(String userNameСustomer) {
        username.setValue(userNameСustomer);
        return this;
    }

    @Step("Заполнить поле Password")
    public pages.RegisterPage setValuePassword(String passwordСustomer) {
        password.setValue(passwordСustomer);
        return this;
    }

    @Step("Заполнить поле Confirm")
    public pages.RegisterPage setValueConfirm(String passwordСustomer) {
        confirm.setValue(passwordСustomer);
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

