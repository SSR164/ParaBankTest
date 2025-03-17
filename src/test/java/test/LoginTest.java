package test;

import api.LoginApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dto.User;
import factory.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import utils.RandomUtils;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
@Tag("test")
public class LoginTest extends TestBase {
    private final LoginPage loginPage = new LoginPage();
    private final AccountPage accountPage = new AccountPage();
    private final RandomUtils randomUtils = new RandomUtils();
    private final LoginApi loginApi = new LoginApi();
    private final UserFactory userFactory = new UserFactory();


    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=True,Password=True")
    void loggingUserTruePasswordTrueUITest() {
        User user = userFactory.getUserFixed();
        loginPage.openPage();
        loginPage.setValueUsername(user.getUserName());
        loginPage.setValuePassword(user.getPassword());
        loginPage.clickLogin();
        accountPage.checkAccount(user.getFirstName() + " " + user.getLastName());
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=True,Password=False")
    void loginUserTruePasswordFalseUITest() {
        User user = userFactory.getUserFixed();
        loginPage.openPage();
        loginPage.setValueUsername(user.getUserName());
        loginPage.setValuePassword(randomUtils.getPassword());
        loginPage.clickLogin();
        loginPage.checkErrorLogin();
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=False,Password=True")
    void loginUserFalsePasswordTrueUITest() {
        User user = userFactory.getUserFixed();
        loginPage.openPage();
        loginPage.setValueUsername(randomUtils.getUserName());
        loginPage.setValuePassword(user.getPassword());
        loginPage.clickLogin();
        loginPage.checkErrorLogin();
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=False,Password=False")
    void loginUserFalsePasswordFalseUITest() {
        loginPage.openPage();
        loginPage.setValueUsername(randomUtils.getUserName());
        loginPage.setValuePassword(randomUtils.getPassword());
        loginPage.clickLogin();
        loginPage.checkErrorLogin();
    }


    @Test
    @Tag("WEB+API")
    @DisplayName("Проверка авторизации на UI через API")
    public void testLogin() {
        User user = userFactory.getUserFixed();
        Response response = loginApi.getJSESSIONID(user.getUserName(), user.getPassword());
        String sessionId = response.getCookie("JSESSIONID");
        loginPage.loginPageRegisteredPerson(sessionId);
        loginPage.openPage();
        accountPage.checkAccount(user.getFirstName() + " " + user.getLastName());
    }

    @Test
    @Tag("API")
    @DisplayName("Проверка авторизации через API")
    void fixedAccountCustomerIDTest() throws JsonProcessingException {
        User user = userFactory.getUserFixed();//Создали пользователя которого будем проверять
        Response response = loginApi.getLogin(user.getUserName(), user.getPassword());//Залогинились под пользователем
        String responseXml= response.getBody().asString();//достали xml которую прислал be
        XmlMapper xmlMapper = new XmlMapper();//Созали объект xml мапера
        User responsedUser = xmlMapper.readValue(responseXml, User.class);//Наполняет объект responsedUser данными из responseXml приведенными к типу User.class
        assertThat(responsedUser).usingRecursiveComparison().ignoringFields("id") //сравниваем responsedUser и user , игнорим id userName password
                .ignoringFields("userName")
                .ignoringFields("password")
                .isEqualTo(user);

    }


}

