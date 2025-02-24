package test;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoggingPage;
import pages.RegisterPage;



@Tag("tech")
public class TechnicalTest extends TestBase {
    LoggingPage loggingPage = new LoggingPage();
    AccountPage accountPage = new AccountPage();
    RegisterPage registerPage = new RegisterPage();
    String staticFirstName = "Albus";
    String staticLastName = "Dumbledore";
    String staticAddress = "Room of Requirement 742";
    String staticCity = "Hogsmeade";
    String staticState = "Scotland";
    String staticZipCode = "HM309 7HP";
    String staticPhone = "44 7872345612";
    String staticSSN = "DA42S12345";
    String staticUsername = "albusgryffindor";
    String staticPassword = "ExpectoPatronum789";

    @Test
    @Tag("UI")
    // @Disabled("Тест для генерации данных.Parabank ежедневно чистит БД.")
    @DisplayName("Регистрация аккаунта для генерации тестовых данных")
    void sataticRegisterAccountMaxUITest() {
        loggingPage.openPage();
        loggingPage.clickRegister();
        registerPage.setValueFirstName(staticFirstName);
        registerPage.setValueLastName(staticLastName);
        registerPage.setValueAddress(staticAddress);
        registerPage.setValueCity(staticCity);
        registerPage.setValueState(staticState);
        registerPage.setValueZipCode(staticZipCode);
        registerPage.setValuePhone(staticPhone);
        registerPage.setValueSSN(staticSSN);
        registerPage.setValueUsernName(staticUsername);
        registerPage.setValuePassword(staticPassword);
        registerPage.setValueConfirm(staticPassword);
        registerPage.clickRegister();
        accountPage.checkСreateAccount(staticUsername);

    }

}
