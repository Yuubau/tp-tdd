package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor

public class DrivingLicenceModificationService {
    private final InMemoryDatabase db = InMemoryDatabase.getInstance();

    private DrivingLicenceFinderService drivingLicenceFinderService;
    public DrivingLicence removeDrivingLicencePoint(UUID drivingLicenceUUID, int numberOfPointsToRemove) {
        Optional<DrivingLicence> drivingLicenceOptional = drivingLicenceFinderService.findById(drivingLicenceUUID);
        if(drivingLicenceOptional.isPresent()) {
            DrivingLicence drivingLicence = drivingLicenceOptional.get();
            int soustractedPoints = drivingLicence.getAvailablePoints() - numberOfPointsToRemove;
            return db.save(drivingLicenceUUID, drivingLicence.withavailablePoints(Math.max(soustractedPoints, 0)));
        } else {
            throw new ResourceNotFoundException("No corresponding driving licence");
        }
    }
}
