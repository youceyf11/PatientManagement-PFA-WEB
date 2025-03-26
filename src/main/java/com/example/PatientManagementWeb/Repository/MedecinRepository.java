package com.example.PatientManagementWeb.Repository;

import com.example.PatientManagementWeb.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedecinRepository extends JpaRepository<Medecin, UUID> {
}
