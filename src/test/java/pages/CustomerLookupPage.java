package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CustomerLookupPage {
    @Step("Заполнить поле FirstName")
    public CustomerLookupPage setValueFirstName(String firstName){
        $("#firstName").setValue(firstName);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public CustomerLookupPage setValueLastName(String lastName){
        $("#lastName").setValue(lastName);
        return this;
    }
    @Step("Заполнить поле Address")
    public CustomerLookupPage setValueAddress(String address){
        $("[name='address.street']").setValue(address);
        return this;
    }
    @Step("Заполнить поле City")
    public CustomerLookupPage setValueCity (String city){
        $("[name='address.city']").setValue(city);
        return this;
    }
    @Step("Заполнить поле State")
    public CustomerLookupPage setValueState (String state){
        $("[name='address.state']").setValue(state);
        return this;
    }
    @Step("Заполнить поле Zip Code")
    public CustomerLookupPage setValueZipCode (String zipCode){
        $("[name='address.zipCode']").setValue(zipCode);
        return this;
    }
    @Step("Заполнить поле SSN")
    public CustomerLookupPage setValueSSN (String ssn){
        $("[name='ssn']").setValue(ssn);
        return this;
    }
    @Step("Нажать на кнопку Find My Login Info")
    public CustomerLookupPage clickFindMyLoginInfo (){
        $("input.button[value='Find My Login Info']").click();
        return this;
    }
}
