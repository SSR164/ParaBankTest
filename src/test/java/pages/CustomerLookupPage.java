package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


public class CustomerLookupPage {

    private final SelenideElement firstName = $("#lookupForm").$("#firstName");
    private final SelenideElement lastName = $("#lookupForm").$("#lastName");
    private final SelenideElement address = $("#lookupForm").$("[name='address.street']");
    private final SelenideElement city = $("#lookupForm").$("[name='address.city']");
    private final SelenideElement state = $("#lookupForm").$("[name='address.state']");
    private final SelenideElement zipCode = $("#lookupForm").$("[name='address.zipCode']");
    private final SelenideElement ssn = $("#lookupForm").$("[name='ssn']");
    private final SelenideElement findMyLoginInfo = $("input.button[value='Find My Login Info']");


    @Step("Заполнить поле FirstName")
    public CustomerLookupPage setValueFirstName(String firstNameCustomer) {
        firstName.setValue(firstNameCustomer);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public CustomerLookupPage setValueLastName(String lastNameCustomer) {
        lastName.setValue(lastNameCustomer);
        return this;
    }

    @Step("Заполнить поле Address")
    public CustomerLookupPage setValueAddress(String addressCustomer) {
        address.setValue(addressCustomer);
        return this;
    }

    @Step("Заполнить поле City")
    public CustomerLookupPage setValueCity(String cityCustomer) {
        city.setValue(cityCustomer);
        return this;
    }

    @Step("Заполнить поле State")
    public CustomerLookupPage setValueState(String stateCustomer) {
        state.setValue(stateCustomer);
        return this;
    }

    @Step("Заполнить поле Zip Code")
    public CustomerLookupPage setValueZipCode(String zipCodeCustomer) {
        zipCode.setValue(zipCodeCustomer);
        return this;
    }

    @Step("Заполнить поле SSN")
    public CustomerLookupPage setValueSSN(String ssnCustomer) {
        ssn.setValue(ssnCustomer);
        return this;
    }

    @Step("Нажать на кнопку Find My Login Info")
    public CustomerLookupPage clickFindMyLoginInfo() {
        findMyLoginInfo.click();
        return this;
    }
}
