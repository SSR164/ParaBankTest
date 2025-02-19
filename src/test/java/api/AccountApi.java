package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static helpers.CustomApiListener.withCustomTemplates;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AccountApi {
    @Step("Получить список счетов пользователя")
    public Response getCustomerAccounts(String customerId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .pathParam("customerId", customerId)  // Передаём customerId в путь
                .when()
                .get("/parabank/services/bank/customers/{customerId}/accounts") // <-- передаем параметры в URL
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }
    @Step("Проверить какому пользователю принадлежит счет")
    public Response getAccounts(String accountsId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .filter(withCustomTemplates())
                .log().all()
                .pathParam("accountsId", accountsId)
                .when()
                .get("/parabank/services/bank/accounts/{accountsId}") // <-- передаем параметры в URL
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }
    @Step("Подать заявку на кредит")
    public Response getRegistLoan(String customerId,String amount,String downPayment,String fromAccountId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .filter(withCustomTemplates())
                .log().all()
                .queryParam("customerId",customerId)
                .queryParam("amount",amount)
                .queryParam("downPayment",downPayment)
                .queryParam("fromAccountId",fromAccountId)
                .when()
                .post("/parabank/services/bank/requestLoan") // <-- передаем параметры в URL
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }
    @Step("Получить общую сумму денег у пользователя ")
    public Double getBalanceAll(Response response) {
        List<Double> balances = response.xmlPath().getList("accounts.account.balance", Double.class);
        Double balanceAll=0.0;
        for (int i = 0; i < balances.size(); i++) {
            balanceAll = balanceAll + balances.get(i);

        }
        return balanceAll;
    }
    @Step("Получить ожидаемую сумму денег у пользователя после получения кредита ")
    public Double getExpectedBalancel(Double balanceAll,Double amount,Double downPayment ) {
        Double expectedBalancel=balanceAll+amount-downPayment;
        return expectedBalancel;
    }
    @Step("Подать заявку на кредит")
    public Response updateCustomer(String customerId,String firstName,String lastName,String street,String city,String state,String zipCode,String phoneNumber,String ssn,String username,String password) {
        Response response = given()
                .contentType(ContentType.JSON)
                .filter(withCustomTemplates())
                .log().all()
                .pathParam("customerId",customerId)
                .queryParam("firstName",firstName)
                .queryParam("lastName",lastName)
                .queryParam("street",street)
                .queryParam("city",city)
                .queryParam("state",state)
                .queryParam("zipCode",zipCode)
                .queryParam("phoneNumber",phoneNumber)
                .queryParam("ssn",ssn)
                .queryParam("username",username)
                .queryParam("password",password)
                .when()
                .post("/parabank/services/bank/customers/update/{customerId}") // <-- передаем параметры в URL
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }
    @Step("Получить количество счетов у пользователя")
    public int getNumberAccounts(Response response) {
        List<Object> accounts = response.xmlPath().getList("accounts.account"); // Убираем Integer.class
        return accounts.size();
    }
    @Step("Подать заявку на кредит")
    public Response creatAccounts(String customerId,int newAccountType,String fromAccountId  ) {
        Response response = given()
                .contentType(ContentType.JSON)
                .filter(withCustomTemplates())
                .log().all()
                .queryParam("customerId",customerId)
                .queryParam("newAccountType",newAccountType)
                .queryParam("fromAccountId",fromAccountId )
                .when()
                .post("/parabank/services/bank/createAccount") // <-- передаем параметры в URL
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }
}
