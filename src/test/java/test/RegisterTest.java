package test;

import config.UserConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.CustomerLookupPage;
import pages.LoggingPage;
import pages.RegisterPage;
import utils.RandomUtils;

@Tag("test")
public class RegisterTest extends TestBase {

    LoggingPage loggingPage = new LoggingPage();
    AccountPage accountPage = new AccountPage();
    RegisterPage registerPage = new RegisterPage();
    RandomUtils randomUtils = new RandomUtils();
    CustomerLookupPage customerLookupPage = new CustomerLookupPage();
    String randomPassword = randomUtils.getPassword();
    String randomUsernName = randomUtils.getUsernName();
    String nameField = "Username";
    String staticFirstName = "Albus";
    String staticLastName = "Dumbledore";
    String staticAddress = "Room of Requirement 742";
    String staticCity = "Hogsmeade";
    String staticState = "Scotland";
    String staticZipCode = "HM309 7HP";
    String staticSSN = "DA42S12345";
    String staticUsername = UserConfig.getUserName();
    String staticPassword = UserConfig.getPassword();

    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, заполняем все поля")
    void randomRegisterAccountUITest() {
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(randomUtils.getFirstName());
        registerPage.setValueLastName(randomUtils.getLastName());
        registerPage.setValueAddress(randomUtils.getAddress());
        registerPage.setValueCity(randomUtils.getCity());
        registerPage.setValueState(randomUtils.getState());
        registerPage.setValueZipCode(randomUtils.getZipCode());
        registerPage.setValuePhone(randomUtils.getPhone());
        registerPage.setValueSSN(randomUtils.getSSN());
        registerPage.setValueUsernName(randomUsernName);
        registerPage.setValuePassword(randomPassword);
        registerPage.setValueConfirm(randomPassword);
        registerPage.clickRegister();
        accountPage.checkСreateAccount(randomUsernName);

    }


    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, заполняем только обязательные поля")
    void randomRegisterAccountMimUITest() {
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(randomUtils.getFirstName());
        registerPage.setValueLastName(randomUtils.getLastName());
        registerPage.setValueAddress(randomUtils.getAddress());
        registerPage.setValueCity(randomUtils.getCity());
        registerPage.setValueState(randomUtils.getState());
        registerPage.setValueZipCode(randomUtils.getZipCode());
        registerPage.setValueSSN(randomUtils.getSSN());
        registerPage.setValueUsernName(randomUsernName);
        registerPage.setValuePassword(randomPassword);
        registerPage.setValueConfirm(randomPassword);
        registerPage.clickRegister();
        accountPage.checkСreateAccount(randomUsernName);

    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, поле Username не заполнено")
    void registerAccountFieldNotUITest() {
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(randomUtils.getFirstName());
        registerPage.setValueLastName(randomUtils.getLastName());
        registerPage.setValueAddress(randomUtils.getAddress());
        registerPage.setValueCity(randomUtils.getCity());
        registerPage.setValueState(randomUtils.getState());
        registerPage.setValueZipCode(randomUtils.getZipCode());
        registerPage.setValueSSN(randomUtils.getSSN());
        registerPage.setValuePassword(randomPassword);
        registerPage.setValueConfirm(randomPassword);
        registerPage.clickRegister();
        accountPage.checkFieldNotFilled(nameField);
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, пароли не совпадают")
    void registerAccountPasswordsNotMatchUITest() {
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(randomUtils.getFirstName());
        registerPage.setValueLastName(randomUtils.getLastName());
        registerPage.setValueAddress(randomUtils.getAddress());
        registerPage.setValueCity(randomUtils.getCity());
        registerPage.setValueState(randomUtils.getState());
        registerPage.setValueZipCode(randomUtils.getZipCode());
        registerPage.setValueSSN(randomUtils.getSSN());
        registerPage.setValuePassword(randomUtils.getPassword());
        registerPage.setValueConfirm(randomUtils.getPassword());
        registerPage.clickRegister();
        accountPage.checkPasswordsNotMatch();
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка кнопки \"Забыли пароль?\"")
    void forgotLoginInfo() {
        loggingPage.openPage();
        registerPage.clickForgotLoginInfo();
        customerLookupPage.setValueFirstName(staticFirstName);
        customerLookupPage.setValueLastName(staticLastName);
        customerLookupPage.setValueAddress(staticAddress);
        customerLookupPage.setValueCity(staticCity);
        customerLookupPage.setValueState(staticState);
        customerLookupPage.setValueZipCode(staticZipCode);
        customerLookupPage.setValueSSN(staticSSN);
        customerLookupPage.clickFindMyLoginInfo();
        accountPage.checkLoginInformation();
        accountPage.checkUsername(staticUsername);
        accountPage.checkPassword(staticPassword);


    }
}
