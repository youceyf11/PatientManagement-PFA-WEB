package com.example.PatientManagementWeb.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
public class Patient extends User{

    @ManyToOne
    private Medecin medecin;

    private int age;
    private String gender;
    private String address;
    private String medicalHistory;



}
