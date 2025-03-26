package com.example.PatientManagementWeb.Entity;

import jakarta.persistence.Entity;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class Patient extends User{

    private Medecin medecin;
    private int age;
    private String gender;
    private String address;
    private Long phone;
    private String medicalHistory;



}
