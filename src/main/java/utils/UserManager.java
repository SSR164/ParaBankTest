package utils;

import com.codeborne.selenide.Configuration;
import dto.User;
import factory.UserFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class UserManager {
    @BeforeAll
    static void configuration() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://parabank.parasoft.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.remote = System.getProperty("remoteUrl");
    }


    public void createUserIfNotExist() {
        User user = new UserFactory().getUserFixed();
        Login login = new Login();

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .pathParam("username", user.getUserName())
                .pathParam("password", user.getPassword())
                .when()
                .get("https://parabank.parasoft.com/parabank/services/bank/login/{username}/{password}")
                .then()
                .log().all()
                .extract().response();
        if (response.getStatusCode() != HttpStatus.SC_OK) {
            login.openPageRepeat();
            login.clickRegisterRepeat();
            login.setValueFirstNameRepeat(user.getFirstName());
            login.setValueLastNameRepeat(user.getLastName());
            login.setValueAddressRepeat(user.getAddress().getStreet());
            login.setValueCityRepeat(user.getAddress().getCity());
            login.setValueStateRepeat(user.getAddress().getState());
            login.setValueZipCodeRepeat(user.getAddress().getZipCode());
            login.setValuePhoneRepeat(user.getPhoneNumber());
            login.setValueSSNRepeat(user.getSsn());
            login.setValueUserNameRepeat(user.getUserName());
            login.setValuePasswordRepeat(user.getPassword());
            login.setValueConfirmRepeat(user.getPassword());
            login.clickRegister();
        }


    }
}
