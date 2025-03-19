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
            login.openPage1();
            login.clickRegister1();
            login.setValueFirstName1(user.getFirstName());
            login.setValueLastName1(user.getLastName());
            login.setValueAddress1(user.getAddress().getStreet());
            login.setValueCity1(user.getAddress().getCity());
            login.setValueState1(user.getAddress().getState());
            login.setValueZipCode1(user.getAddress().getZipCode());
            login.setValuePhone1(user.getPhoneNumber());
            login.setValueSSN1(user.getSsn());
            login.setValueUserName1(user.getUserName());
            login.setValuePassword1(user.getPassword());
            login.setValueConfirm1(user.getPassword());
            login.clickRegister();
        }


    }
}
