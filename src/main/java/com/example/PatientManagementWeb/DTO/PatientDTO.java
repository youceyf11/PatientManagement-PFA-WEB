package com.example.PatientManagementWeb.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PatientDTO extends UserDTO{

    private String medecinId;
    private int age;
    private String gender;
    private String adresse;
    private String medicalHistory;
}
