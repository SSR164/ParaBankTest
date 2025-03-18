package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
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
import config.UserConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class TestBase {
    static WebDriverConfig config;

    @BeforeAll
    static void beforeAll() {
        String env = System.getProperty("env", "local");
        System.out.println("Environment: " + env);
        try (InputStream input = TestBase.class.getClassLoader().getResourceAsStream(env + ".properties")) {
            if (input == null) {
                throw new RuntimeException("Could not find properties file: " + env + ".properties");
            }

            Properties props = new Properties();
            props.load(input);
            System.getProperties().putAll(props);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file: " + env + ".properties", e);
        }
        config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());


        boolean isUiTests = !"false".equals(System.getProperty("uiTests", "true"));
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.defaultParser = Parser.JSON;
        //RestAssured.authentication = RestAssured.basic(
        //UserConfig.getUserName(),
        //UserConfig.getPassword()

        //);
        if (isUiTests) {
            Configuration.baseUrl = config.getBaseUrl();
            Configuration.headless = false;
            Configuration.pageLoadStrategy = config.getLoadStrategy();
            Configuration.holdBrowserOpen = false;
            Configuration.browser = config.getBrowser();
            Configuration.browserVersion = config.getBrowserVersion();
            Configuration.browserSize = config.getbrowserSize();
            Configuration.remote = config.getRemoteUrl();
            System.setProperty("junit.jupiter.execution.parallel.enabled", "false");


            if (config.getIsRemote()) {
                Configuration.remote = config.getRemoteUrl();

                DesiredCapabilities capabilities = new DesiredCapabilities();

                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true

                ));

                Configuration.browserCapabilities = capabilities;

            } else {
                // Отключаем WebDriver, если это API-тесты
                Configuration.remote = null;
            }
        } else {

            Configuration.browser = "none";
        }

    }

    @BeforeEach
    void beforeEach() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        System.out.println("Executing addAttachments()...");

        if (!Configuration.browser.equals("none") && WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        } else {
            System.out.println("Skipping attachments. Either WebDriver is not started or browser is set to 'none'.");
        }


        if (!Configuration.browser.equals("none") && WebDriverRunner.hasWebDriverStarted()) {
            System.out.println("Closing WebDriver...");
            Selenide.closeWebDriver();
        }
    }

}






