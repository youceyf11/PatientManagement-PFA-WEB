package com.example.PatientManagementWeb.repository;

import com.example.PatientManagementWeb.entity.Medecin;
import com.example.PatientManagementWeb.entity.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, UUID> {
    boolean findByMedecin(Medecin medecin);
}
