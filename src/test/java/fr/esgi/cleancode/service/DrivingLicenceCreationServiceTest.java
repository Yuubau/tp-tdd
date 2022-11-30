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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationServiceTest {

    @InjectMocks
    private DrivingLicenceCreationService service;

    @Mock
    private SocialSecurityNumberValidationService socialSecurityNumberValidationService;

    @Mock
    private DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;

    @Mock
    private InMemoryDatabase database;

    @Test
    void valid_datas_should_create_driving_licence() {
        String socialSecurityNumber = "123456789101112";
        when(socialSecurityNumberValidationService.verifySecurityNumberValidity(socialSecurityNumber)).thenReturn(true);
        DrivingLicence drivingLicence = service.createDrivingLicence(socialSecurityNumber);
        assertThat(drivingLicence.getAvailablePoints()).equalsTo(12);
        assertThat(drivingLicence.getDriverSocialSecurityNumber()).equalsTo(socialSecurityNumber);

    }

    @Test
    void invalid_datas_should_create_driving_licence() {
        String socialSecurityNumber = "1234567891012";
        when(socialSecurityNumberValidationService.verifySecurityNumberValidity(socialSecurityNumber)).thenThrow(InvalidDriverSocialSecurityNumberException.class);
        DrivingLicence drivingLicence = service.createDrivingLicence(socialSecurityNumber));
        assertThat(drivingLicence.getAvailablePoints()).equalsTo(12);
        assertThat(drivingLicence.getDriverSocialSecurityNumber()).equalsTo(socialSecurityNumber);
    }

}
