package com.example.PatientManagementWeb.Repository;

import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Entity.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, UUID> {
    boolean findByMedecin(Medecin medecin);
}
