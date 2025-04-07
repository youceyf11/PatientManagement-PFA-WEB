package com.example.PatientManagementWeb.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MedecinDTO extends UserDTO{

    private String specialty;

    @NotBlank(message = "localisation is mandatory")
    private String location;
}
