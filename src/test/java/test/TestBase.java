package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll //Метод с аннотацией @BeforeAll вызывается до выполнения первого теста в классе.
    static void beforeAll() {//@BeforeAll требует static, т.к выполняется один раз перед всеми тестами в классе, метод связан с классом.
        Configuration.baseUrl = "https://parabank.parasoft.com";// Устанавливает базовый URL для тестов. Это означает, что все относительные пути в тестах (например, "/path") будут строиться на основе этого URL.
        Configuration.headless = false;  // Включает обычный режим (с UI)
        Configuration.pageLoadStrategy = "eager";// Определяет стратегию загрузки страницы. "eager" означает, что тест начнет выполняться сразу после загрузки DOM, без ожидания загрузки всех ресурсов (например, изображений и CSS).
        Configuration.holdBrowserOpen = true;// Задает, чтобы браузер оставался открытым после выполнения теста. Это полезно для отладки, чтобы проверить состояние страницы после теста
        Configuration.browser = System.getProperty("browser", "chrome");// Определяет браузер для запуска тестов. Если параметр `browser` передан через командную строку, он будет использован, иначе используется "chrome" по умолчанию.
        Configuration.browserVersion = System.getProperty("browserVersion");// Устанавливает версию браузера, если она передана через параметры командной строки. Полезно при работе с удаленными WebDriver (например, Selenoid или Selenium Grid).
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");// Устанавливает размер окна браузера. Если параметр `browserSize` передан через командную строку, используется он, иначе размер окна будет "1920x1080" по умолчанию.
        Configuration.remote = System.getProperty("remoteUrl");// Устанавливает удаленный URL для WebDriver (например, для запуска тестов через Selenoid или Selenium Grid). Если параметр `remoteUrl` передан, он будет использован.
        System.setProperty("junit.jupiter.execution.parallel.enabled", "false");
        RestAssured.baseURI = "https://parabank.parasoft.com";
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterEach
    void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().quit();
        }
    }

}





