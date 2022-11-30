package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceModificationServiceTest {

    @InjectMocks
    private DrivingLicenceModificationService service;

    @Mock
    private DrivingLicenceFinderService drivingLicenceFinderService;


    @Test
    void remove_point_existing_driving_licence() {
        UUID drivingLicenceUUID = UUID.fromString("eef93bc4-70c1-11ed-a1eb-0242ac120002");
        when(drivingLicenceFinderService.findById(drivingLicenceUUID)).thenReturn(Optional.ofNullable(DrivingLicence.builder().id(drivingLicenceUUID).availablePoints(12).build()));

        DrivingLicence drivingLicence = service.removeDrivingLicencePoint(drivingLicenceUUID, 12);

        assertThat(drivingLicence.getAvailablePoints())
                .isEqualTo(0);
    }

    @Test
    void remove_point_non_existing_driving_licence() {
        UUID drivingLicenceUUID = UUID.fromString("eef93bc4-70c1-11ed-a1eb-0242ac120002");
        when(drivingLicenceFinderService.findById(drivingLicenceUUID)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
            service.removeDrivingLicencePoint(drivingLicenceUUID, 12);
        });
    }

    @Test
    void remove_too_much_point_from_driving_licence() {
        UUID drivingLicenceUUID = UUID.fromString("eef93bc4-70c1-11ed-a1eb-0242ac120002");
        when(drivingLicenceFinderService.findById(drivingLicenceUUID)).thenReturn(Optional.ofNullable(DrivingLicence.builder().id(drivingLicenceUUID).availablePoints(8).build()));

        DrivingLicence drivingLicence = service.removeDrivingLicencePoint(drivingLicenceUUID, 12);

        assertThat(drivingLicence.getAvailablePoints())
                .isEqualTo(0);
    }
}