package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static specs.ApiSpecs.*;

public class LoggingApi {
    @Step("Авторизация через post для получения JSESSIONID")
    public Response getJSESSIONID(String username, String password) {
        Response response = given(RequestSpec)
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")  // Указываем формат данных
                .formParam("username", username)          // Передача логина
                .formParam("password", password)      // Передача пароля
                .log().all()
                .when()
                .post("/parabank/login.htm")                      // Делаем POST-запрос
                .then()
                .statusCode(302)                                  // Проверяем, что редирект успешен
                .header("Location", containsString("overview.htm")) // Проверяем, что переходим на главную
                .cookie("JSESSIONID", notNullValue())// Убедимся, что сессия установлена
                .spec(userResponseSpecification302)
                .extract().response();
        return response;
    }

    @Step("Авторизация через get")
    public Response getlogging(String username, String password) {
        Response response = given(RequestSpec)
                .contentType(ContentType.JSON)
                .filter(withCustomTemplates())
                .log().all()
                .pathParam("username", username)  // Передаём username в путь
                .pathParam("password", password)  // Передаём password в путь
                .when()
                .get("/parabank/services/bank/login/{username}/{password}") // <-- передаем параметры в URL
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }
}

