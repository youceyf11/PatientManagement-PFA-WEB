package com.example.PatientManagementWeb.Repository;

import com.example.PatientManagementWeb.Entity.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SecretaryRepository extends JpaRepository<Secretary, UUID> {
}
