package test;


import dto.User;
import factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.CustomerLookupPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.RandomUtils;


@Tag("test")
public class RegisterTest extends TestBase {

   private final LoginPage loggingPage = new LoginPage();
   private final AccountPage accountPage = new AccountPage();
   private final RegisterPage registerPage = new RegisterPage();
   private final RandomUtils randomUtils = new RandomUtils();
   private final UserFactory userFactory = new UserFactory();
   private final CustomerLookupPage customerLookupPage = new CustomerLookupPage();


    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, заполняем все поля")
    void randomRegisterAccountUITest() {
        User user = userFactory.getUser();
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(user.getFirstName());
        registerPage.setValueLastName(user.getLastName());
        registerPage.setValueAddress(user.getAddress().getStreet());
        registerPage.setValueCity(user.getAddress().getCity());
        registerPage.setValueState(user.getAddress().getState());
        registerPage.setValueZipCode(user.getAddress().getZipCode());
        registerPage.setValuePhone(user.getPhoneNumber());
        registerPage.setValueSSN(user.getSsn());
        registerPage.setValueUserName(user.getUserName());
        registerPage.setValuePassword(user.getPassword());
        registerPage.setValueConfirm(user.getPassword());
        registerPage.clickRegister();
        accountPage.checkCreateAccount(user.getUserName());

    }


    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, заполняем только обязательные поля")
    void randomRegisterAccountMimUITest() {
        User user = userFactory.getUser();
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(user.getFirstName());
        registerPage.setValueLastName(user.getLastName());
        registerPage.setValueAddress(user.getAddress().getStreet());
        registerPage.setValueCity(user.getAddress().getCity());
        registerPage.setValueState(user.getAddress().getState());
        registerPage.setValueZipCode(user.getAddress().getZipCode());
        registerPage.setValueSSN(user.getSsn());
        registerPage.setValueUserName(user.getUserName());
        registerPage.setValuePassword(user.getPassword());
        registerPage.setValueConfirm(user.getPassword());
        registerPage.clickRegister();
        accountPage.checkCreateAccount(user.getUserName());

    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, поле Username не заполнено")
    void registerAccountFieldNotUITest() {
        User user = userFactory.getUser();
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(user.getFirstName());
        registerPage.setValueLastName(user.getLastName());
        registerPage.setValueAddress(user.getAddress().getStreet());
        registerPage.setValueCity(user.getAddress().getCity());
        registerPage.setValueState(user.getAddress().getState());
        registerPage.setValueZipCode(user.getAddress().getZipCode());
        registerPage.setValueSSN(user.getSsn());
        registerPage.setValuePassword(user.getPassword());
        registerPage.setValueConfirm(user.getPassword());
        registerPage.clickRegister();
        accountPage.checkFieldNotFilled("Username");
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка регистрации аккаунта с рандомными данными, пароли не совпадают")
    void registerAccountPasswordsNotMatchUITest() {
        User user = userFactory.getUser();
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(user.getFirstName());
        registerPage.setValueLastName(user.getLastName());
        registerPage.setValueAddress(user.getAddress().getStreet());
        registerPage.setValueCity(user.getAddress().getCity());
        registerPage.setValueState(user.getAddress().getState());
        registerPage.setValueZipCode(user.getAddress().getZipCode());
        registerPage.setValueSSN(user.getSsn());
        registerPage.setValuePassword(user.getPassword());
        registerPage.setValueConfirm(randomUtils.getPassword());
        registerPage.clickRegister();
        accountPage.checkPasswordsNotMatch();
    }

    @Test
    @Tag("WEB")
    @DisplayName("Проверка кнопки \"Забыли пароль?\"")
    void forgotLoginInfo() {
        User user = userFactory.getUserFixed();
        loggingPage.openPage();
        registerPage.clickForgotLoginInfo();
        customerLookupPage.setValueFirstName(user.getFirstName());
        customerLookupPage.setValueLastName(user.getLastName());
        customerLookupPage.setValueAddress(user.getAddress().getStreet());
        customerLookupPage.setValueCity(user.getAddress().getCity());
        customerLookupPage.setValueState(user.getAddress().getState());
        customerLookupPage.setValueZipCode(user.getAddress().getZipCode());
        customerLookupPage.setValueSSN(user.getSsn());
        customerLookupPage.clickFindMyLoginInfo();
        accountPage.checkLoginInformation();
        accountPage.checkUsername(user.getUserName());
        accountPage.checkPassword(user.getPassword());


    }
}
