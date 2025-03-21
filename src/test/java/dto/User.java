package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String ssn;
    private String userName;
    private String password;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;
    }
}