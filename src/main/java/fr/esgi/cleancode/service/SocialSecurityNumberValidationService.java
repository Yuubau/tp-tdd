package fr.esgi.cleancode.service;

public class SocialSecurityNumberValidationService {

    public Boolean verifySecurityNumberValidity(String socialSecurityNumber) {
        if(socialSecurityNumber == null) {
            return false;
        }
        return socialSecurityNumber.matches("[0-9]{15}");
    }

}
