package utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class Login {
    private final SelenideElement loginPaneRepeat = $("#loginPanel");
    private final SelenideElement firstNameRepeat = $("[name='customer.firstName']");
    private final SelenideElement lastNameRepeat = $("[name='customer.lastName']");
    private final SelenideElement addressRepeat = $("[name='customer.address.street']");
    private final SelenideElement cityRepeat = $("[name='customer.address.city']");
    private final SelenideElement stateRepeat = $("[name='customer.address.state']");
    private final SelenideElement zipCodeRepeat = $("[name='customer.address.zipCode']");
    private final SelenideElement phoneNumberRepeat = $("[name='customer.phoneNumber']");
    private final SelenideElement ssnRepeat = $("[name='customer.ssn']");
    private final SelenideElement usernameRepeat = $("[name='customer.username']");
    private final SelenideElement passwordRepeat = $("[name='customer.password']");
    private final SelenideElement confirmRepeat = $("[name='repeatedPassword']");
    private final SelenideElement registerRepeat = $("#customerForm").$(".button");


    @Step("Открыть страницу")
    public Login openPageRepeat() {
        open("/parabank/index.htm");
        return this;
    }

    @Step("Нажать на кнопку LOG IN ")
    public Login clickRegisterRepeat() {
        loginPaneRepeat.$(byText("Register")).click();
        return this;
    }

    @Step("Заполнить поле FirstName")//
    public Login setValueFirstNameRepeat(String firstNameCustomer) {
        firstNameRepeat.setValue(firstNameCustomer);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public Login setValueLastNameRepeat(String lastNameCustomer) {
        lastNameRepeat.setValue(lastNameCustomer);
        return this;
    }

    @Step("Заполнить поле Address")
    public Login setValueAddressRepeat(String addressCustomer) {
        addressRepeat.setValue(addressCustomer);
        return this;
    }

    @Step("Заполнить поле City")
    public Login setValueCityRepeat(String cityCustomer) {
        cityRepeat.setValue(cityCustomer);
        return this;
    }

    @Step("Заполнить поле State")
    public Login setValueStateRepeat(String stateCustomer) {
        stateRepeat.setValue(stateCustomer);
        return this;
    }

    @Step("Заполнить поле Zip Code")
    public Login setValueZipCodeRepeat(String zipCodeCustomer) {
        zipCodeRepeat.setValue(zipCodeCustomer);
        return this;
    }

    @Step("Заполнить поле Phone")
    public Login setValuePhoneRepeat(String phoneCustomer) {
        phoneNumberRepeat.setValue(phoneCustomer);
        return this;
    }

    @Step("Заполнить поле SSN")
    public Login setValueSSNRepeat(String ssnCustomer) {
        ssnRepeat.setValue(ssnCustomer);
        return this;
    }

    @Step("Заполнить поле Username")
    public Login setValueUserNameRepeat(String userNameCustomer) {
        usernameRepeat.setValue(userNameCustomer);
        return this;
    }

    @Step("Заполнить поле Password")
    public Login setValuePasswordRepeat(String passwordCustomer) {
        passwordRepeat.setValue(passwordCustomer);
        return this;
    }

    @Step("Заполнить поле Confirm")
    public Login setValueConfirmRepeat(String passwordCustomer) {
        confirmRepeat.setValue(passwordCustomer);
        return this;
    }

    @Step("Нажать на кнопку Register")
    public Login clickRegister() {
        registerRepeat.click();
        return this;

    }


}
