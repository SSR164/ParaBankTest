package web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.UserConfig;
import config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.UserManager;

import java.util.Map;


public class TestBaseWeb {


    static WebDriverConfig config;

    @BeforeAll
    static void configuration() {

        config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.pageLoadStrategy = config.getLoadStrategy();
        Configuration.holdBrowserOpen = false;
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getbrowserSize();
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.authentication = RestAssured.basic(
                UserConfig.getUserName(),
                UserConfig.getPassword()
        );

        if (config.getIsRemote()) {
            Configuration.remote = config.getRemoteUrl();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        UserManager userManager = new UserManager();
        userManager.createUserIfNotExist();
    }


    @BeforeEach
    void addAllureListener() {
        SelenideLogger.removeListener("AllureSelenide");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();

    }
}


