package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountPage {
    @Step("Авторизация через get")
    public Response getCustomerAccounts(String username, String password) {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .pathParam("username", username)  // Передаём username в путь
                .pathParam("password", password)  // Передаём password в путь
                .when()
                .get("/parabank/services/bank/customers/{username}/") // <-- передаем параметры в URL
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }
}
