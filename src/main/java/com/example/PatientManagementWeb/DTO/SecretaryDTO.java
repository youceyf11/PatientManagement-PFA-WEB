package com.example.PatientManagementWeb.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SecretaryDTO extends UserDTO{
    @NotBlank(message = "medecin id is mandatory")
    private String medecinId;

    @NotBlank(message = "departement is mandatory")
    private String departament;
}
