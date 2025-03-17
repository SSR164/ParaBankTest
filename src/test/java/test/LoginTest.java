package test;

import api.LoginApi;
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
import static org.hamcrest.Matchers.equalTo;

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
    void fixedAccountCustomerIDTest() {
        User user = userFactory.getUser();
        Response response = loginApi.getLogin(user.getUserName(), user.getPassword());
        String firstName = response.xmlPath().getString("customer.firstName");
        assertThat(firstName, equalTo(user.getFirstName()));
        String lastName = response.xmlPath().getString("customer.lastName");
        assertThat(lastName, equalTo(user.getLastName()));
        String street = response.xmlPath().getString("customer.address.street");
        assertThat(street, equalTo(user.getAddress().getStreet()));
        String city = response.xmlPath().getString("customer.address.city");
        assertThat(city, equalTo(user.getAddress().getCity()));
        String state = response.xmlPath().getString("customer.address.state");
        assertThat(state, equalTo(user.getAddress().getState()));
        String zipCode = response.xmlPath().getString("customer.address.zipCode");
        assertThat(zipCode, equalTo(user.getAddress().getZipCode()));
        String phoneNumber = response.xmlPath().getString("customer.phoneNumber");
        assertThat(phoneNumber, equalTo(user.getPhoneNumber()));
        String ssn = response.xmlPath().getString("customer.ssn");
        assertThat(ssn, equalTo(user.getSsn()));

    }


}

