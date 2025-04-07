package com.example.PatientManagementWeb.Enum;


import com.example.PatientManagementWeb.Exceptions.ProfessionalException;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Statut {
    PLANIFIE,
    EN_COURS,
    TERMINE,
    ANNULE,
    REPORTE,
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
}
