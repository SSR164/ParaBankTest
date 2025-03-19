package test;

import api.AccountApi;
import api.LoginApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import dto.User;
import factory.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.UserCheckUtils;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountsApiTest extends TestBaseApi {
    private final LoginApi loginApi = new LoginApi();
    private final AccountApi accountApi = new AccountApi();
    private final UserFactory userFactory = new UserFactory();
    private String customerId;


    @BeforeEach
    void getCustomerId() {
        User user = userFactory.getUserFixed();
        Response response = loginApi.getLogin(user.getUserName(), user.getPassword());
        customerId = response.xmlPath().getString("customer.id");
    }

    @Test
    @Tag("API")
    @DisplayName("Получить список счетов для пользователя, проверить что все счета в списке принадлежат пользователю")
    void checkCustomerAccountsTest() {
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
    void getCheckCustomerAccountsTest() {
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
    void getAndCheckCustomerAccountsTest() {
        Response response1 = accountApi.getCustomerAccounts(customerId);
        String accountId = response1.xmlPath().getString("accounts.account[0].id");
        Double balanceAll = accountApi.getBalanceAll(response1);
        accountApi.getRegisterLoan(customerId, "1500", "1", accountId);
        Double expectedBalance = accountApi.getExpectedBalance(balanceAll, 1500.0, 1.0);
        Response response2 = accountApi.getCustomerAccounts(customerId);
        Double balanceAfterLoan = accountApi.getBalanceAll(response2);
        step("Проверяем, что ожидаемая сумма равна фактической ", () -> assertThat(expectedBalance, equalTo(balanceAfterLoan)));
    }

    @Test
    @Tag("API")
    @DisplayName("Проверка процедуры обновления информацию о клиенте")
    void updateCustomerInformationTest() throws JsonProcessingException {
        User user = userFactory.getUser();
        User userFixed = userFactory.getUserFixed();
        accountApi.updateCustomer(customerId, user);
        Response response = loginApi.getLogin(user.getUserName(), user.getPassword());
        String responseXml = response.getBody().asString();
        UserCheckUtils.checkFields(responseXml, user);
        accountApi.updateCustomer(customerId, userFixed);

    }
}
