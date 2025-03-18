package test;

import api.LoggingApi;
import config.UserConfig;
import dto.User;
import factory.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoggingPage;
import pages.RegisterPage;
import utils.RandomUtils;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Tag("test")
public class LoggingTest extends TestBase {
    LoggingPage loggingPage = new LoggingPage();
    AccountPage accountPage = new AccountPage();
    RandomUtils randomUtils = new RandomUtils();
    LoggingApi loggingApi = new LoggingApi();
    UserFactory userFactory= new UserFactory();
    //String staticFullName = "Albus Dumbledore";


    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=True,Password=True")
    void loggingUserTruePasswordTrueUITest() {
        User user=userFactory.getUserFixed();
        loggingPage.openPage();
        loggingPage.setValueUsername(user.getUserName());
        loggingPage.setValuePassword(user.getPassword());
        loggingPage.clickLogin();
        accountPage.checkAccount(user.getFirstName()+" "+user.getLastName());
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=True,Password=False")
    void loggingUserTruePasswordFalseUITest() {
        User user=userFactory.getUserFixed();
        loggingPage.openPage();
        loggingPage.setValueUsername(user.getUserName());
        loggingPage.setValuePassword(randomUtils.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=False,Password=True")
    void loggingUserFalsePasswordTrueUITest() {
        User user=userFactory.getUserFixed();
        loggingPage.openPage();
        loggingPage.setValueUsername(randomUtils.getUsernName());
        loggingPage.setValuePassword(user.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка авторизации в системе User=False,Password=False")
    void loggingUserFalsePasswordFalsUITest() {
        loggingPage.openPage();
        loggingPage.setValueUsername(randomUtils.getUsernName());
        loggingPage.setValuePassword(randomUtils.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }


    @Test
    @Tag("WEB+API")
    @DisplayName("Проверка авторизации на UI через API")
    public void testLogin() {
        User user=userFactory.getUserFixed();
        Response response = loggingApi.getJSESSIONID(user.getUserName(), user.getPassword());
        String sessionId = response.getCookie("JSESSIONID");
        loggingPage.loginPageRegisteredPerson(sessionId);
        loggingPage.openPage();
        accountPage.checkAccount(user.getFirstName()+" "+user.getLastName());
    }

    @Test
    @Tag("API")
    @DisplayName("Проверка авторизации через API")
    void sataticAccountCustomerIDTest() {
        User user=userFactory.getUser();
        Response response = loggingApi.getlogging(user.getUserName(), user.getPassword());
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

