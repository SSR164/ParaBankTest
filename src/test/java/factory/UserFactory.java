package factory;

import config.UserConfig;
import dto.User;
import utils.RandomUtils;

public class UserFactory {
    RandomUtils randomUtils = new RandomUtils();
    //String fixedUsername = UserConfig.getUserName();
    //String fixedPassword = UserConfig.getPassword();
    String fixedFirstName = "Albus";
    String fixedLastName = "Dumbledore";
    String fixedAddress = "Room of Requirement 742";
    String fixedCity = "Hogsmeade";
    String fixedState = "Scotland";
    String fixedZipCode = "HM309 7HP";
    String fixedPhone = "44 7872345612";
    String fixedSSN = "DA42S12345";
    String fixedUsernName= "albusgryffindor";
    String fixedPassword="12345";

    public User getUser() {
        return User
                .builder()
                .firstName(randomUtils.getFirstName())
                .lastName(randomUtils.getLastName())
                .address(User.Address.builder()
                        .street(randomUtils.getAddress())
                        .city(randomUtils.getCity())
                        .state(randomUtils.getState())
                        .zipCode(randomUtils.getZipCode())
                        .build())
                .phoneNumber(randomUtils.getPhone())
                .ssn(randomUtils.getSSN())
                .userName(randomUtils.getUsernName())
                .password(randomUtils.getPassword())
                .build();
    }
    public User getUserFixed() {
        return User
                .builder()
                .firstName(fixedFirstName)
                .lastName(fixedLastName)
                .address(User.Address.builder()
                        .street(fixedAddress)
                        .city(fixedCity)
                        .state(fixedState)
                        .zipCode((fixedZipCode))
                        .build())
                .phoneNumber(fixedPhone )
                .ssn(fixedSSN)
                .userName(fixedUsernName )
                .password(fixedPassword)
                .build();
    }
}
