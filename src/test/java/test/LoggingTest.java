package test;

import api.LoggingApi;
import config.UserConfig;
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
    String staticFullName = "Albus Dumbledore";
    String staticUsername = UserConfig.getUserName();
    String staticPassword = UserConfig.getPassword();
    String staticFirstName = "Albus";
    String staticLastName = "Dumbledore";
    String staticAddress = "Room of Requirement 742";
    String staticCity = "Hogsmeade";
    String staticState = "Scotland";
    String staticZipCode = "HM309 7HP";
    String staticPhone = "44 7872345612";
    String staticSSN = "DA42S12345";

    @Test
    @Tag("UI")
    @DisplayName("Авторизация в системе User=True,Password=True")
    void loggingUserTruePasswordTrueUITest() {
        loggingPage.openPage();
        loggingPage.setValueUsername(staticUsername);
        loggingPage.setValuePassword(staticPassword);
        loggingPage.clickLogin();
        accountPage.checkAccount(staticFullName);
    }

    @Test
    @Tag("UI")
    @DisplayName("Авторизация в системе User=True,Password=False")
    void loggingUserTruePasswordFalseUITest() {
        loggingPage.openPage();
        loggingPage.setValueUsername(staticUsername);
        loggingPage.setValuePassword(randomUtils.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }

    @Test
    @Tag("UI")
    @DisplayName("Авторизация в системе User=False,Password=True")
    void loggingUserFalsePasswordTrueUITest() {
        loggingPage.openPage();
        loggingPage.setValueUsername(randomUtils.getUsernName());
        loggingPage.setValuePassword(staticPassword);
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }

    @Test
    @Tag("UI")
    @DisplayName("Авторизация в системе User=False,Password=False")
    void loggingUserFalsePasswordFalsUITest() {
        loggingPage.openPage();
        loggingPage.setValueUsername(randomUtils.getUsernName());
        loggingPage.setValuePassword(randomUtils.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }


    @Test
    @Tag("API+UI")
    @DisplayName("Авторизация на UI через API")
    public void testLogin() {
        Response response = loggingApi.getJSESSIONID(staticUsername, staticPassword);
        String sessionId = response.getCookie("JSESSIONID");
        loggingPage.loginPageRegisteredPerson(sessionId);
        loggingPage.openPage();
        accountPage.checkAccount(staticFullName);
    }
    @Test
    @Tag("API")
    @DisplayName("Авторизация через API")
    void sataticAccountCustomerIDTest() {
        Response response = loggingApi.getlogging(staticUsername,staticPassword);
        String firstName = response.xmlPath().getString("customer.firstName");
        assertThat(firstName, equalTo(staticFirstName));
        String lastName = response.xmlPath().getString("customer.lastName");
        assertThat(lastName, equalTo(staticLastName));
        String street = response.xmlPath().getString("customer.address.street");
        assertThat(street, equalTo(staticAddress));
        String city = response.xmlPath().getString("customer.address.city");
        assertThat(city, equalTo(staticCity));
        String state = response.xmlPath().getString("customer.address.state");
        assertThat(state, equalTo(staticState));
        String zipCode = response.xmlPath().getString("customer.address.zipCode");
        assertThat(zipCode, equalTo(staticZipCode));
        String phoneNumber = response.xmlPath().getString("customer.phoneNumber");
        assertThat(phoneNumber, equalTo(staticPhone));
        String ssn = response.xmlPath().getString("customer.ssn");
        assertThat(ssn, equalTo(staticSSN));

    }


}

