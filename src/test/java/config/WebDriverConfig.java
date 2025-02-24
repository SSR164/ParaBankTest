package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",// Использует файл, указанный через переменную окружения "env"
        "classpath:local.properties"   // Файл по умолчанию, если "env" не передан или файл не найден
})

public interface WebDriverConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://parabank.parasoft.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("latest")
    String getBrowserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean getIsRemote();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemoteUrl();

    @Key("loadStrategy")
    @DefaultValue("eager")
    String getLoadStrategy();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getbrowserSize();

    @Key("staticUsername")
    @DefaultValue("albusgryffindor")
    String getStaticUsername();

    @Key("staticPassword")
    @DefaultValue("ExpectoPatronum789")
    String getStaticPassword();

    @Key("staticFirstName")
    @DefaultValue("Albus")
    String getStaticFirstName();

    @Key("staticLastName")
    @DefaultValue("Dumbledore")
    String getStaticLastName();

    @Key("staticAddress")
    @DefaultValue("Room of Requirement 742")
    String getStaticAddress();

    @Key("staticCity")
    @DefaultValue("Hogsmeade")
    String getStaticCity();

    @Key("staticState")
    @DefaultValue("Scotland")
    String getStaticState();

    @Key("staticZipCode")
    @DefaultValue("HM309 7HP")
    String getStaticZipCode();

    @Key("staticPhone")
    @DefaultValue("44 7872345612")
    String getStaticPhone();

    @Key("staticSSN ")
    @DefaultValue("DA42S12345")
    String getStaticSSN ();
}
