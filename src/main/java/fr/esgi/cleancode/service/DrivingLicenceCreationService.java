package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class DrivingLicenceCreationService {
    private final InMemoryDatabase db = InMemoryDatabase.getInstance();
    public DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;
    public SocialSecurityNumberValidationService socialSecurityNumberValidationService;

    public DrivingLicence createDrivingLicence(String socialSecurityNumber) {
        if(socialSecurityNumberValidationService.verifySecurityNumberValidity(socialSecurityNumber)) {
            DrivingLicence drivingLicence =  DrivingLicence.builder().id(this.drivingLicenceIdGenerationService.generateNewDrivingLicenceId())
                    .availablePoints(12).driverSocialSecurityNumber(socialSecurityNumber).build();
            return db.save(drivingLicence.getId(), drivingLicence);
        } else {
            throw new InvalidDriverSocialSecurityNumberException("Numéro de sécurité invalide, création du permis de conduire impossible");
        }
    }
}
