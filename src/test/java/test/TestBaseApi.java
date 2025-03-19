package test;


import config.UserConfig;
import config.WebDriverConfig;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseApi {

    static WebDriverConfig config;

    @BeforeAll
    public static void setUp() {
        config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.defaultParser = Parser.JSON;
                RestAssured.authentication = RestAssured.basic(
        UserConfig.getUserName(),
        UserConfig.getPassword()
        );
    }
}
