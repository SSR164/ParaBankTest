package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dto.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserCheckUtils {
    public static void checkFields(String responseXml, User comparisonUser) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        User responsedUser = xmlMapper.readValue(responseXml, User.class);
        assertThat(responsedUser).usingRecursiveComparison().ignoringFields("id")
                .ignoringFields("userName")
                .ignoringFields("password")
                .isEqualTo(comparisonUser);
    }
}
