package com.example.PatientManagementWeb.repository;


import com.example.PatientManagementWeb.entity.RendezVous;
import com.example.PatientManagementWeb.Enum.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, UUID> {

    List<RendezVous> findByPatientId(UUID patientId);
    List<RendezVous> findByMedecinId(UUID medicineId);

    List<RendezVous> findByStatut(Statut statut);
    List<RendezVous> findByMedecinIdAndDateHeure(UUID medecinId, LocalDateTime dateHeure);

    Statut Statut(Statut statut);
}
