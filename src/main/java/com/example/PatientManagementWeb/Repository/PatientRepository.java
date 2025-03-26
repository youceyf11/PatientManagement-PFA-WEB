package com.example.PatientManagementWeb.Repository;

import com.example.PatientManagementWeb.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
