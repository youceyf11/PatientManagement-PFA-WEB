package com.example.PatientManagementWeb.Repository;

import com.example.PatientManagementWeb.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
