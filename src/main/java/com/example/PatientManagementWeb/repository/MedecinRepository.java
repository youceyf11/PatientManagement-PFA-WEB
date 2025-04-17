package com.example.PatientManagementWeb.repository;

import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, UUID> {
    List<Medecin> findBySpecialty(String specialty);
}
