package utils;

import net.datafaker.Faker;

import java.util.Locale;


public class RandomUtils {
    final Faker faker = new Faker(new Locale("en"));

    public String getFirstName() {
        return faker.name().firstName();
    }


    public String getLastName() {
        return faker.name().lastName();
    }

    public String getAddress() {
        return faker.address().streetAddressNumber();
    }

    public String getCity() {
        return faker.address().city();
    }

    public String getState() {
        return faker.address().state();
    }

    public String getZipCode() {
        return faker.address().zipCode();
    }

    public String getPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public String getSSN() {
        return faker.idNumber().ssnValid();
    }

    public String getUserName() {
        return faker.harryPotter().character();
    }

    public String getPassword() {
        return faker.internet().password(12, 16, true, false, true);
    }
}