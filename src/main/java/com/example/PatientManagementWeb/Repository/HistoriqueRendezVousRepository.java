package com.example.PatientManagementWeb.Repository;

 import com.example.PatientManagementWeb.Entity.HistoriqueRendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoriqueRendezVousRepository extends JpaRepository<HistoriqueRendezVous, String> {

    List<HistoriqueRendezVous> findByPatientId(String patientId);
    List<HistoriqueRendezVous> findByMedecinId(String medecinId);
    List<HistoriqueRendezVous> findByMedecinSpecialite(String specialite);
    List<HistoriqueRendezVous> findByDateTimeBetween(LocalDateTime debut, LocalDateTime fin);
    List<HistoriqueRendezVous> findByStatut(String statut);
}