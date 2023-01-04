package fr.esgi.cleancode.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class DrivingLicence {
    UUID id;
    String driverSocialSecurityNumber;

    @With
    @Default
    int availablePoints = 12;

    public DrivingLicence withavailablePoints(int newPoints) {
        return DrivingLicence.builder().availablePoints(newPoints)
                .id(this.id).driverSocialSecurityNumber(this.driverSocialSecurityNumber)
                .build();
    }
}
