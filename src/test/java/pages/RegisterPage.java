package pages;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    @Step("Заполнить поле FirstName")
    public RegisterPage setValueFirstName(String firstName){
        $("[name='customer.firstName']").setValue(firstName);
        return this;
    }

    @Step("Заполнить поле Last Name")
    public pages.RegisterPage setValueLastName(String lastName){
        $("[name='customer.lastName']").setValue(lastName);
        return this;
        }
    @Step("Заполнить поле Address")
    public pages.RegisterPage setValueAddress(String address){
        $("[name='customer.address.street']").setValue(address);
        return this;
    }
    @Step("Заполнить поле City")
    public pages.RegisterPage setValueCity (String city){
        $("[name='customer.address.city']").setValue(city);
        return this;
    }
    @Step("Заполнить поле State")
    public pages.RegisterPage setValueState (String state){
        $("[name='customer.address.state']").setValue(state);
        return this;
    }
    @Step("Заполнить поле Zip Code")
    public pages.RegisterPage setValueZipCode (String zipCode){
        $("[name='customer.address.zipCode']").setValue(zipCode);
        return this;
    }
    @Step("Заполнить поле Phone")
    public pages.RegisterPage setValuePhone (String phone){
        $("[name='customer.phoneNumber']").setValue(phone);
        return this;
    }
    @Step("Заполнить поле SSN")
    public pages.RegisterPage setValueSSN (String ssn){
        $("[name='customer.ssn']").setValue(ssn);
        return this;
    }
    @Step("Заполнить поле Username")
    public pages.RegisterPage setValueUsernName (String userName){
        $("[name='customer.username']").setValue(userName);
        return this;
    }
    @Step("Заполнить поле Password")
    public pages.RegisterPage setValuePassword (String password){
        $("[name='customer.password']").setValue(password);
        return this;
    }
    @Step("Заполнить поле Confirm")
    public pages.RegisterPage setValueConfirm (String password){
        $("[name='repeatedPassword']").setValue(password);
        return this;
    }
    @Step("Нажать на кнопку Register")
    public pages.RegisterPage clickRegister (){
        $("#customerForm").$(".button").click();
        return this;
    }
    }

