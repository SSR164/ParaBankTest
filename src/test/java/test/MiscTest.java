package test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoggingPage;
import pages.RegisterPage;
import utils.RandomUtils;

import static io.restassured.RestAssured.given;

public class MiscTest extends TestBase {
    LoggingPage loggingPage =new LoggingPage();
    AccountPage accountPage=new AccountPage();
    RegisterPage registerPage=new RegisterPage();
    RandomUtils randomUtils = new RandomUtils();
    String staticFirstName= "Albus";
    String staticLastName= "Dumbledore";
    String staticAddress= "Room of Requirement 742";
    String staticCity= "Hogsmeade";
    String staticState="Scotland";
    String staticZipCode= "HM309 7HP";
    String staticPhone=  "+44 7872345612";
    String staticSSN="DA42S12345";
    String staticUsername="albusgryffindor";
    String staticPassword="ExpectoPatronum789";
    String randomPassword= randomUtils.getPassword();
    String randomUsernName= randomUtils.getUsernName();
    String nameField ="Username";



    @Test
    //@Disabled("Тест отключен ")
    @DisplayName("Авторизация в системе User=True,Password=False")
    void userTruePasswordFalseUITest(){
        loggingPage.openPage();
        loggingPage.setValueUsername(staticUsername);
        loggingPage.setValuePassword(randomUtils.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }
    @Test
    @DisplayName("Авторизация в системе User=True,Password=True")
    void userTruePasswordTrueUITest(){
        loggingPage.openPage(); // Если эта строка не выполнится, значит тест не стартует!
        loggingPage.setValueUsername(staticUsername);
        loggingPage.setValuePassword(staticPassword);
        loggingPage.clickLogin();
        accountPage.checkAccount();
    }

    @Test
    //@Disabled("Тест отключен ")
    @DisplayName("Авторизация в системе User=False,Password=True")
    void userFalsePasswordTrueUITest(){
        loggingPage.openPage();
        loggingPage.setValueUsername(randomUtils.getUsernName());
        loggingPage.setValuePassword(staticPassword);
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }
    @Test
    //@Disabled("Тест отключен ")
    @DisplayName("Авторизация в системе User=False,Password=False")
    void userFalsePasswordFalsUITest(){
        loggingPage.openPage();
        loggingPage.setValueUsername(randomUtils.getUsernName());
        loggingPage.setValuePassword(randomUtils.getPassword());
        loggingPage.clickLogin();
        loggingPage.checkErrorLogin();
    }
    @Test
    //@Disabled("Тест отключен ")
    @DisplayName("Регистрация аккаунта с рандомными данными, заполняем все поля ")
    void randomRegisterAccountUITest(){
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
    @Disabled("Тест для генерации данных.Parabank ежедневно чистит БД.")
    @DisplayName("Регистрация аккаунта для генерации тестовых данных")
    void sataticRegisterAccountMaxUITest(){
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
    @Test
    //@Disabled("Тест отключен ")
    @DisplayName("Регистрация аккаунта с рандомными данными, заполняем только обязательные поля")
    void randomRegisterAccountMimUITest(){
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
    //@Disabled("Тест отключен ")
    @DisplayName("Регистрация аккаунта с рандомными данными, поле Username не заполнено")
    void registerAccountFieldNotUITest(){
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
    //@Disabled("Тест отключен ")
    @DisplayName("Регистрация аккаунта с рандомными данными, пароли не совпадают")
    void registerAccountPasswordsNotMatchUITest(){
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

}
