package com.example.PatientManagementWeb.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SecretaryDTO extends UserDTO{
    private String medecinId;
    private String departament;
}
