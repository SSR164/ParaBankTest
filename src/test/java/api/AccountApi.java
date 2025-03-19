package api;


import dto.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.ApiSpecs.RequestSpec;
import static specs.ApiSpecs.userResponseSpecification200;

public class AccountApi {
    @Step("Получить список счетов пользователя")
    public Response getCustomerAccounts(String customerId) {
        Response response = given(RequestSpec)
                .pathParam("customerId", customerId)  // Передаём customerId в путь
                .when()
                .get("/parabank/services/bank/customers/{customerId}/accounts") // <-- передаем параметры в URL
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Проверить какому пользователю принадлежит счет")
    public Response getAccounts(String accountsId) {
        Response response = given(RequestSpec)
                .pathParam("accountsId", accountsId)
                .when()
                .get("/parabank/services/bank/accounts/{accountsId}") // <-- передаем параметры в URL
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Подать заявку на кредит")
    public void getRegisterLoan(String customerId, String amount, String downPayment, String fromAccountId) {
        Response response = given(RequestSpec)
                .queryParam("customerId", customerId)
                .queryParam("amount", amount)
                .queryParam("downPayment", downPayment)
                .queryParam("fromAccountId", fromAccountId)
                .when()
                .post("/parabank/services/bank/requestLoan") // <-- передаем параметры в URL
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
    }

    @Step("Получить общую сумму денег у пользователя ")
    public Double getBalanceAll(Response response) {
        List<Double> balances = response.xmlPath().getList("accounts.account.balance", Double.class);
        double balanceAll = 0.0;
        for (Double balance : balances) {
            balanceAll = balanceAll + balance;

        }
        return balanceAll;
    }

    @Step("Получить ожидаемую сумму денег у пользователя после получения кредита ")
    public Double getExpectedBalance(Double balanceAll, Double amount, Double downPayment) {
        Double expectedBalance = balanceAll + amount - downPayment;
        return expectedBalance;
    }

    @Step("Подать заявку на кредит")
    public void updateCustomer(String customerId, User user) {
        Response response = given(RequestSpec)
                .pathParam("customerId", customerId)
                .queryParam("firstName", user.getFirstName())
                .queryParam("lastName", user.getLastName())
                .queryParam("street", user.getAddress().getStreet())
                .queryParam("city", user.getAddress().getCity())
                .queryParam("state", user.getAddress().getState())
                .queryParam("zipCode", user.getAddress().getZipCode())
                .queryParam("phoneNumber", user.getPhoneNumber())
                .queryParam("ssn", user.getSsn())
                .queryParam("username", user.getUserName())
                .queryParam("password", user.getPassword())
                .when()
                .post("/parabank/services/bank/customers/update/{customerId}") // <-- передаем параметры в URL
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
    }

    @Step("Получить количество счетов у пользователя")
    public int getNumberAccounts(Response response) {
        List<Object> accounts = response.xmlPath().getList("accounts.account"); // Убираем Integer.class
        return accounts.size();
    }

    @Step("Подать заявку на кредит")
    public void createAccounts(String customerId, int newAccountType, String fromAccountId) {
        Response response = given(RequestSpec)
                .queryParam("customerId", customerId)
                .queryParam("newAccountType", newAccountType)
                .queryParam("fromAccountId", fromAccountId)
                .when()
                .post("/parabank/services/bank/createAccount") // <-- передаем параметры в URL
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
    }
}
