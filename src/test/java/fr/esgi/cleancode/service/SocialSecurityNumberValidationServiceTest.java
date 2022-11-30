package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SocialSecurityNumberValidationServiceTest {

    @Mock
    private SocialSecurityNumberValidationService service;

    @Test
    void valid_security_number_should_verify() {
        assertThat(service.verifySecurityNumberValidity("093847362543253")).isTrue();
    }

    @Test
    void null_security_number_should_fail() {
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> {
            service.verifySecurityNumberValidity(null);
        });
    }

    @Test
    void alpha_numerical_security_number_should_fail() {
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> {
            service.verifySecurityNumberValidity("ahd635476354hfudh");
        });
    }

    @Test
    void security_number_size_13_should_fail() {
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> {
            service.verifySecurityNumberValidity("2435463726354");
        });
    }

}
