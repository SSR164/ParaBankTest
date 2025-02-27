package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$;


public class CustomerLookupPage {
    private SelenideElement firstName = $("#lookupForm").$("#firstName"),
            lastName = $("#lookupForm").$("#lastName"),
            address = $("#lookupForm").$("[name='address.street']"),
            city = $("#lookupForm").$("[name='address.city']"),
            state = $("#lookupForm").$("[name='address.state']"),
            zipCode = $("#lookupForm").$("[name='address.zipCode']"),
            ssn = $("#lookupForm").$("[name='ssn']"),
            findMyLoginInfo = $("input.button[value='Find My Login Info']");


    @Step("Заполнить поле FirstName")
    public CustomerLookupPage setValueFirstName(String firstNameСustomer) {
        firstName.setValue(firstNameСustomer);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public CustomerLookupPage setValueLastName(String lastNameСustomer) {
        lastName.setValue(lastNameСustomer);
        return this;
    }

    @Step("Заполнить поле Address")
    public CustomerLookupPage setValueAddress(String addressСustomer) {
        address.setValue(addressСustomer);
        return this;
    }

    @Step("Заполнить поле City")
    public CustomerLookupPage setValueCity(String cityСustomer) {
        city.setValue(cityСustomer);
        return this;
    }

    @Step("Заполнить поле State")
    public CustomerLookupPage setValueState(String stateСustomer) {
        state.setValue(stateСustomer);
        return this;
    }

    @Step("Заполнить поле Zip Code")
    public CustomerLookupPage setValueZipCode(String zipCodeСustomer) {
        zipCode.setValue(zipCodeСustomer);
        return this;
    }

    @Step("Заполнить поле SSN")
    public CustomerLookupPage setValueSSN(String ssnСustomer) {
        ssn.setValue(ssnСustomer);
        return this;
    }

    @Step("Нажать на кнопку Find My Login Info")
    public CustomerLookupPage clickFindMyLoginInfo() {
        findMyLoginInfo.click();
        return this;
    }
}
