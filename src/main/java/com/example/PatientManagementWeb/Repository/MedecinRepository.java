package com.example.PatientManagementWeb.Repository;

import com.example.PatientManagementWeb.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, UUID> {
    List<Medecin> findBySpecialty(String specialty);
}
