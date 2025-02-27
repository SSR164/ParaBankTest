package test;

import api.AccountApi;
import api.LoggingApi;
import config.UserConfig;
import config.WebDriverConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoggingPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("test")
public class AccountsTest extends TestBase {
    UserConfig userConfig = new UserConfig();
    LoggingApi loggingApi = new LoggingApi();
    AccountApi accountApi = new AccountApi();
    LoggingPage loggingPage = new LoggingPage();
    AccountPage accountPage = new AccountPage();
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
    RandomUtils randomUtils = new RandomUtils();
    String randomFirstName = randomUtils.getFirstName();
    String randomLastName = randomUtils.getLastName();
    String randomStreet = randomUtils.getAddress();
    String randomCity = randomUtils.getCity();
    String randomState = randomUtils.getState();
    String randomZipCode = randomUtils.getZipCode();
    String randomphoneNumber = randomUtils.getPhone();
    String randomSSN = randomUtils.getSSN();
    String randomPassword = randomUtils.getPassword();
    String randomUsernName = randomUtils.getUsernName();


    @Test
    @Tag("API")
    @DisplayName("Получить список счетов для пользователя, проверить что все счета в списке принадлежат пользователю")
    void checkCustomerAccountsTest() {
        Response response = loggingApi.getlogging(staticUsername, staticPassword);
        String customerId = response.xmlPath().getString("customer.id");
        Response response1 = accountApi.getCustomerAccounts(customerId);
        List<String> accountIds = response1.xmlPath().getList("accounts.account.id");
        step("Проверяем, что все счета принадлежат пользователю", () -> {
            for (String accountId : accountIds) {
                Response response2 = accountApi.getAccounts(accountId);
                String id = response2.xmlPath().getString("account.customerId");
                assertThat(id, equalTo(customerId));
            }
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Проверит, наличия счетов у пользователя")
    void getСheckCustomerAccountsTest() {
        Response response = loggingApi.getlogging(staticUsername, staticPassword);
        String customerId = response.xmlPath().getString("customer.id");
        Response response1 = accountApi.getCustomerAccounts(customerId);
        step("Проверяем, что пользователю есть счета", () -> {
            if (response1 == null || response1.getBody() == null || response1.getBody().asString().isEmpty()) {
                fail("Ошибка: ответ от API пустой!");
            }
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Проверка процедуры получения кредита")
    void getAndСheckCustomerAccountsTest() {
        Response response = loggingApi.getlogging(staticUsername, staticPassword);
        String customerId = response.xmlPath().getString("customer.id");
        Response response1 = accountApi.getCustomerAccounts(customerId);
        String accountId = response1.xmlPath().getString("accounts.account[0].id");
        Double balanceAll = accountApi.getBalanceAll(response1);
        accountApi.getRegistLoan(customerId, "1500", "1", accountId);
        Double expectedBalancel = accountApi.getExpectedBalancel(balanceAll, 1500.0, 1.0);
        Response response2 = accountApi.getCustomerAccounts(customerId);
        Double balanceAfterLoan = accountApi.getBalanceAll(response2);
        step("Проверяем, что ожидаемая сумма равна фактической ", () -> {
            assertThat(expectedBalancel, equalTo(balanceAfterLoan));
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Проверка процедуры обновления информацию о клиенте")
    void updateCustomerInformationTest() {
        Response response2 = loggingApi.getlogging(staticUsername, staticPassword);
        String customerId = response2.xmlPath().getString("customer.id");
        accountApi.updateCustomer(customerId, randomFirstName, randomLastName, randomStreet, randomCity, randomState, randomZipCode, randomphoneNumber, randomSSN, randomUsernName, randomPassword);
        Response response = loggingApi.getlogging(randomUsernName, randomPassword);
        String firstName = response.xmlPath().getString("customer.firstName");
        assertThat(firstName, equalTo(randomFirstName));
        String lastName = response.xmlPath().getString("customer.lastName");
        assertThat(lastName, equalTo(randomLastName));
        String street = response.xmlPath().getString("customer.address.street");
        assertThat(street, equalTo(randomStreet));
        String city = response.xmlPath().getString("customer.address.city");
        assertThat(city, equalTo(randomCity));
        String state = response.xmlPath().getString("customer.address.state");
        assertThat(state, equalTo(randomState));
        String zipCode = response.xmlPath().getString("customer.address.zipCode");
        assertThat(zipCode, equalTo(randomZipCode));
        String phoneNumber = response.xmlPath().getString("customer.phoneNumber");
        assertThat(phoneNumber, equalTo(randomphoneNumber));
        String ssn = response.xmlPath().getString("customer.ssn");
        assertThat(ssn, equalTo(randomSSN));
        accountApi.updateCustomer(customerId, staticFirstName, staticLastName, staticAddress, staticCity, staticState, staticZipCode, staticPhone, staticSSN, staticUsername, staticPassword);

    }

    @Test
    @Tag("WEB+API")
    @DisplayName("Проверка процедуры перевода дс с одного счета пользователя на другой")
    void transferMoneyTest() {
        Response response = loggingApi.getlogging(staticUsername, staticPassword);
        String customerId = response.xmlPath().getString("customer.id");
        Response response1 = accountApi.getCustomerAccounts(customerId);
        String accountIds = response1.xmlPath().getString("accounts.account[0].id");
        int numberAccounts = accountApi.getNumberAccounts(response1);
        if (numberAccounts == 1) {
            accountApi.creatAccounts(customerId, 0, accountIds);
        }
        Response response2 = loggingApi.getJSESSIONID(staticUsername, staticPassword);
        String sessionId = response2.getCookie("JSESSIONID");
        loggingPage.loginPageRegisteredPerson(sessionId);
        loggingPage.openPage();
        accountPage.clickTransferFunds();
        accountPage.printAmount("1");
        accountPage.chooseAccount("1");
        accountPage.clickTransfer();
        accountPage.checkTransfer();


    }


}
