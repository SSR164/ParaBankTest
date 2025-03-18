package factory;

import config.UserConfig;
import dto.User;
import utils.RandomUtils;

public class UserFactory {
    final RandomUtils randomUtils = new RandomUtils();

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
                .userName(randomUtils.getUserName())
                .password(randomUtils.getPassword())
                .build();
    }

    public User getUserFixed() {
//        String fixedPassword = "12345";
//        String fixedUserName = "albusgryffindor";
        String fixedSSN = "DA42S12345";
        String fixedPhone = "44 7872345612";
        String fixedZipCode = "HM309 7HP";
        String fixedState = "Scotland";
        String fixedCity = "Hogsmeade";
        String fixedAddress = "Room of Requirement 742";
        String fixedLastName = "Dumbledore";
        String fixedUserName = UserConfig.getUserName();
        String fixedPassword = UserConfig.getPassword();
        String fixedFirstName = "Albus";
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
                .phoneNumber(fixedPhone)
                .ssn(fixedSSN)
                .userName(fixedUserName)
                .password(fixedPassword)
                .build();
    }
}
