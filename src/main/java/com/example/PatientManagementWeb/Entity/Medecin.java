package com.example.PatientManagementWeb.Entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Medecin extends User {

    private String specialty;
    private String location;

}
