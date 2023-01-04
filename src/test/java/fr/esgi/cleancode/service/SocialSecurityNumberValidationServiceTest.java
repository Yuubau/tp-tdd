package fr.esgi.cleancode.service;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialSecurityNumberValidationServiceTest {

    private SocialSecurityNumberValidationService service = new SocialSecurityNumberValidationService();

    @Test
    void valid_security_number_should_be_true() {
        assertThat(service.verifySecurityNumberValidity("093847362543253")).isTrue();
    }

    @Test
    void null_security_number_should_be_false() {
        assertThat(service.verifySecurityNumberValidity(null)).isFalse();
    }

    @Test
    void alpha_numerical_security_number_should_be_false() {
        assertThat(service.verifySecurityNumberValidity("ahd635476354hfudh")).isFalse();

    }

    @Test
    void security_number_size_13_should_be_false() {
        assertThat(service.verifySecurityNumberValidity("2435463726354")).isFalse();

    }

}
