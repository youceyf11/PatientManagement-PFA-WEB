package com.example.PatientManagementWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Secretary extends User {

    @ManyToOne
    private Medecin medecin;

    private String departament;
    private String contactNumber;


}
