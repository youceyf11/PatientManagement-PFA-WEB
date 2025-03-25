package com.example.PatientManagementWeb.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Patient extends User{

    private Medecin medecin;
    private int age;
    private String gender;
    private String address;
    private Long phone;



}
