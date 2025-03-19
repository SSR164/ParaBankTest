package test.api;

import api.LoginApi;
import com.fasterxml.jackson.core.JsonProcessingException;

import dto.User;
import factory.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.UserCheckUtils;


public class LoginApiTest extends TestBaseApi {
    private final LoginApi loginApi = new LoginApi();
    private final UserFactory userFactory = new UserFactory();

    @Test
    @Tag("API")
    @DisplayName("Проверка авторизации через API")
    void fixedAccountCustomerIDTest() throws JsonProcessingException {
        User user = userFactory.getUserFixed();
        Response response = loginApi.getLogin(user.getUserName(), user.getPassword());
        String responseXml = response.getBody().asString();
        UserCheckUtils.checkFields(responseXml, user);


    }
}
