package com.example.PatientManagementWeb.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PatientDTO extends UserDTO{

    @NotBlank(message = "medecin id is mandatory")
    private String medecinId;

    private int age;
    private String gender;
    private String adresse;
    private String medicalHistory;
}
