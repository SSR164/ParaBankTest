package test;

import api.AccountApi;
import api.LoginApi;

import dto.User;
import factory.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;


@Tag("test")
public class AccountsTest extends TestBaseWeb {
    private final LoginApi loginApi = new LoginApi();
    private final AccountApi accountApi = new AccountApi();
    private final LoginPage loginPage = new LoginPage();
    private final AccountPage accountPage = new AccountPage();
    private final UserFactory userFactory = new UserFactory();
    private String customerId;


    @BeforeEach
    void getCustomerId() {
        User user = userFactory.getUserFixed();
        Response response = loginApi.getLogin(user.getUserName(), user.getPassword());
        customerId = response.xmlPath().getString("customer.id");
    }


    @Test
    @Tag("WEB+API")
    @DisplayName("Проверка процедуры перевода дс с одного счета пользователя на другой")
    void transferMoneyTest() {
        User user = userFactory.getUserFixed();
        Response response1 = accountApi.getCustomerAccounts(customerId);
        String accountIds = response1.xmlPath().getString("accounts.account[0].id");
        int numberAccounts = accountApi.getNumberAccounts(response1);
        if (numberAccounts == 1) {
            accountApi.createAccounts(customerId, 0, accountIds);
        }
        Response response2 = loginApi.getJSESSIONID(user.getUserName(), user.getPassword());
        String sessionId = response2.getCookie("JSESSIONID");
        loginPage.loginPageRegisteredPerson(sessionId);
        loginPage.openPage();
        accountPage.clickTransferFunds();
        accountPage.printAmount("1");
        accountPage.chooseAccount(1);
        accountPage.clickTransfer();
        accountPage.checkTransfer();


    }


}
