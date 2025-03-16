package dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String ssn;
    private String userName;
    private String password;

    // Исправлено: добавлен модификатор static
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Address {  // <-- static
        private String street;
        private String city;
        private String state;
        private String zipCode;
    }
}