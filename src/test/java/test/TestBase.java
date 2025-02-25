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
    @BeforeAll //Метод с аннотацией @BeforeAll вызывается до выполнения первого теста в классе.
    static void beforeAll() {//@BeforeAll требует static, т.к выполняется один раз перед всеми тестами в классе, метод связан с классом.
        // Загрузка файла из classpath
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
        RestAssured.authentication = RestAssured.basic(
                UserConfig.getUserName(),
                UserConfig.getPassword()

        );
        if (isUiTests) {
        Configuration.baseUrl = config.getBaseUrl();// Устанавливает базовый URL для тестов. Это означает, что все относительные пути в тестах (например, "/path") будут строиться на основе этого URL.
        Configuration.headless = false;  // Включает обычный режим (с UI)
        Configuration.pageLoadStrategy = config.getLoadStrategy();;// Определяет стратегию загрузки страницы. "eager" означает, что тест начнет выполняться сразу после загрузки DOM, без ожидания загрузки всех ресурсов (например, изображений и CSS).
        Configuration.holdBrowserOpen = false;// Задает, чтобы браузер оставался открытым после выполнения теста. Это полезно для отладки, чтобы проверить состояние страницы после теста
        Configuration.browser = config.getBrowser();// Определяет браузер для запуска тестов. Если параметр `browser` передан через командную строку, он будет использован, иначе используется "chrome" по умолчанию.
        Configuration.browserVersion = config.getBrowserVersion();// Устанавливает версию браузера, если она передана через параметры командной строки. Полезно при работе с удаленными WebDriver (например, Selenoid или Selenium Grid).
        Configuration.browserSize = config.getbrowserSize();// Устанавливает размер окна браузера. Если параметр `browserSize` передан через командную строку, используется он, иначе размер окна будет "1920x1080" по умолчанию.
        Configuration.remote = config.getRemoteUrl();// Устанавливает удаленный URL для WebDriver (например, для запуска тестов через Selenoid или Selenium Grid). Если параметр `remoteUrl` передан, он будет использован.
        System.setProperty("junit.jupiter.execution.parallel.enabled", "false");

// Создаём объект DesiredCapabilities для настройки браузерных возможностей
            if (config.getIsRemote()) {
                Configuration.remote = config.getRemoteUrl();

                DesiredCapabilities capabilities = new DesiredCapabilities();
                // Устанавливаем параметры для Selenoid (удалённого Selenium-сервера)
// Включаем поддержку VNC (для удалённого просмотра сессии) и запись видео тестов
                // Включаем VNC для просмотра браузера в реальном времени
                // Включаем запись видео сеанса тестирования
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true

                ));
                // Применяем настройки браузерных возможностей в Selenide
                Configuration.browserCapabilities = capabilities;

        } else {
            // Отключаем WebDriver, если это API-тесты
            Configuration.remote = null;
        }
        } else {
        // API тесты
        Configuration.browser = "none";
    }

    }

    @BeforeEach
    void beforeEach() {
        // Добавляем слушатель логирования Selenide для Allure (система отчётов)
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        // Проверяем, запущен ли WebDriver перед добавлением вложений
        if (!Configuration.browser.equals("none") && WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
    }

    @AfterEach
    void tearDown() {
        // Закрываем WebDriver только если он был запущен
        if (!Configuration.browser.equals("none") && WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
        }
   }

}






