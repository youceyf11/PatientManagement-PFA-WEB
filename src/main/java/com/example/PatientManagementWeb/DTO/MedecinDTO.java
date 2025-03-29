package com.example.PatientManagementWeb.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MedecinDTO extends UserDTO{
    private String specialty;
    private String location;
}
