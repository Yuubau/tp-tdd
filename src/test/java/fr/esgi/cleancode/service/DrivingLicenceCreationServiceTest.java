package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationServiceTest {

    @InjectMocks
    private DrivingLicenceCreationService service;

    @Mock
    private SocialSecurityNumberValidationService socialSecurityNumberValidationService;

    @Mock
    private DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;

    @Test
    void valid_datas_should_create_driving_licence() {
        String socialSecurityNumber = "123456789101112";
        when(socialSecurityNumberValidationService.verifySecurityNumberValidity(socialSecurityNumber)).thenReturn(true);
        when(drivingLicenceIdGenerationService.generateNewDrivingLicenceId()).thenReturn(UUID.fromString("64b63f0e-85ea-44a0-a654-ee56e80957d3"));

        DrivingLicence drivingLicence = service.createDrivingLicence(socialSecurityNumber);
        assertThat(drivingLicence.getAvailablePoints()).isEqualTo(12);
        assertThat(drivingLicence.getDriverSocialSecurityNumber()).isEqualTo(socialSecurityNumber);

    }

    @Test
    void invalid_datas_should_not_create_driving_licence() {
        String socialSecurityNumber = "1234567891012";
        when(socialSecurityNumberValidationService.verifySecurityNumberValidity(socialSecurityNumber)).thenThrow(InvalidDriverSocialSecurityNumberException.class);
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> {
            DrivingLicence drivingLicence = service.createDrivingLicence(socialSecurityNumber);
        });
   }

    @Test
    void null_datas_should_not_create_driving_licence() {
        String socialSecurityNumber = null;
        when(socialSecurityNumberValidationService.verifySecurityNumberValidity(socialSecurityNumber)).thenThrow(InvalidDriverSocialSecurityNumberException.class);
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> {
            DrivingLicence drivingLicence = service.createDrivingLicence(socialSecurityNumber);
        });
    }

}
