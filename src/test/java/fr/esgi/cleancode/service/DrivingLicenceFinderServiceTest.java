package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_find() {
        UUID drivingLicenceUUID = UUID.fromString("eef93bc4-70c1-11ed-a1eb-0242ac120002");
        when(database.findById(drivingLicenceUUID)).thenReturn(Optional.ofNullable(DrivingLicence.builder().id(drivingLicenceUUID).availablePoints(12).build()));
        Optional driverLicence = service.findById(drivingLicenceUUID);
        assertThat(driverLicence.isPresent()).isTrue();
    }

    @Test
    void should_not_find() {
        UUID drivingLicenceUUID = UUID.fromString("eef93bc4-70c1-11ed-a1eb-0242ac120002");
        when(database.findById(drivingLicenceUUID)).thenReturn(Optional.empty());
        Optional<DrivingLicence> driverLicence = service.findById(drivingLicenceUUID);
        assertThat(driverLicence.isEmpty()).isTrue();
    }
}