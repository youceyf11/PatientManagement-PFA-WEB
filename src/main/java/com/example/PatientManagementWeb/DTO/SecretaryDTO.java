package com.example.PatientManagementWeb.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SecretaryDTO extends UserDTO implements Serializable {
    @NotBlank(message = "medecin id is mandatory")
    private String medecinId;

    @NotBlank(message = "departement is mandatory")
    private String departament;
}
