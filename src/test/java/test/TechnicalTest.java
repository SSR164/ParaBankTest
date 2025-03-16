package test;


import config.UserConfig;
import dto.User;
import factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoggingPage;
import pages.RegisterPage;


@Tag("TECH")
public class TechnicalTest extends TestBase {
    LoggingPage loggingPage = new LoggingPage();
    AccountPage accountPage = new AccountPage();
    RegisterPage registerPage = new RegisterPage();
    UserFactory userFactory=new UserFactory();

    @Test
    // @Disabled("Тест для генерации данных.Parabank ежедневно чистит БД.")
    @DisplayName("Регистрация аккаунта для генерации тестовых данных")
    void sataticRegisterAccountMaxUITest() {
        User user = userFactory. getUserFixed();
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
        registerPage.setValueUsernName(user.getUserName());
        registerPage.setValuePassword(user.getPassword());
        registerPage.setValueConfirm(user.getPassword());
        registerPage.clickRegister();
        accountPage.checkСreateAccount(user.getUserName());

    }

}
