package utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class Login {
    private final SelenideElement loginPanel = $("#loginPanel");
    private final SelenideElement firstName1 = $("[name='customer.firstName']");
    private final SelenideElement lastName1 = $("[name='customer.lastName']");
    private final SelenideElement address1 = $("[name='customer.address.street']");
    private final SelenideElement city1 = $("[name='customer.address.city']");
    private final SelenideElement state1 = $("[name='customer.address.state']");
    private final SelenideElement zipCode1 = $("[name='customer.address.zipCode']");
    private final SelenideElement phoneNumber1 = $("[name='customer.phoneNumber']");
    private final SelenideElement ssn1 = $("[name='customer.ssn']");
    private final SelenideElement username1 = $("[name='customer.username']");
    private final SelenideElement password1 = $("[name='customer.password']");
    private final SelenideElement confirm1 = $("[name='repeatedPassword']");
    private final SelenideElement register1 = $("#customerForm").$(".button");


    @Step("Открыть страницу")
    public Login openPage1() {
        open("/parabank/index.htm");
        return this;
    }

    @Step("Нажать на кнопку LOG IN ")
    public Login clickRegister1() {
        loginPanel.$(byText("Register")).click();
        return this;
    }

    @Step("Заполнить поле FirstName")//
    public Login setValueFirstName1(String firstNameCustomer) {
        firstName1.setValue(firstNameCustomer);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public Login setValueLastName1(String lastNameCustomer) {
        lastName1.setValue(lastNameCustomer);
        return this;
    }

    @Step("Заполнить поле Address")
    public Login setValueAddress1(String addressCustomer) {
        address1.setValue(addressCustomer);
        return this;
    }

    @Step("Заполнить поле City")
    public Login setValueCity1(String cityCustomer) {
        city1.setValue(cityCustomer);
        return this;
    }

    @Step("Заполнить поле State")
    public Login setValueState1(String stateCustomer) {
        state1.setValue(stateCustomer);
        return this;
    }

    @Step("Заполнить поле Zip Code")
    public Login setValueZipCode1(String zipCodeCustomer) {
        zipCode1.setValue(zipCodeCustomer);
        return this;
    }

    @Step("Заполнить поле Phone")
    public Login setValuePhone1(String phoneCustomer) {
        phoneNumber1.setValue(phoneCustomer);
        return this;
    }

    @Step("Заполнить поле SSN")
    public Login setValueSSN1(String ssnCustomer) {
        ssn1.setValue(ssnCustomer);
        return this;
    }

    @Step("Заполнить поле Username")
    public Login setValueUserName1(String userNameCustomer) {
        username1.setValue(userNameCustomer);
        return this;
    }

    @Step("Заполнить поле Password")
    public Login setValuePassword1(String passwordCustomer) {
        password1.setValue(passwordCustomer);
        return this;
    }

    @Step("Заполнить поле Confirm")
    public Login setValueConfirm1(String passwordCustomer) {
        confirm1.setValue(passwordCustomer);
        return this;
    }

    @Step("Нажать на кнопку Register")
    public Login clickRegister() {
        register1.click();
        return this;

    }


}
