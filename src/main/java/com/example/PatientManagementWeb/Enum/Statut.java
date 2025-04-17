package com.example.PatientManagementWeb.Enum;


import com.example.PatientManagementWeb.exceptions.ProfessionalException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Statut {
    SCHEDULED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    POSTPONED,
    ABSENT;

    @JsonCreator
    public static Statut fromString(String value) {
        for(Statut status : Statut.values()) {
            if(status.toString().equals(value)) {
                return status;
            }
        }
        String validValues = Arrays.stream(Statut.values())
                .map(Statut::name)
                .collect(Collectors.joining(", "));
        throw new ProfessionalException("Statut invalide: '" + value + "'. Les valeurs autorisées sont: " + validValues);
    }

    @JsonValue
    public String getValue() {
        return this.name().toLowerCase();  //COMPLETED devient completed...
    }
}
