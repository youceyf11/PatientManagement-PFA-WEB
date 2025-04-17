package com.example.PatientManagementWeb.repository;

 import com.example.PatientManagementWeb.entity.HistoriqueRendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;
 import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
 import java.util.UUID;

@Repository
public interface HistoriqueRendezVousRepository extends JpaRepository<HistoriqueRendezVous, String> {

    List<HistoriqueRendezVous> findByPatient_Id(UUID patient_Id);



    List<HistoriqueRendezVous> findByMedecinId(String medecinId);

    List<HistoriqueRendezVous> findByDateTimeBetween(LocalDateTime debut, LocalDateTime fin);

    List<HistoriqueRendezVous> findByStatut(String statut);

    @Query("SELECT h FROM HistoriqueRendezVous h JOIN Medecin m ON cast(h.medecinId as uuid) = m.id WHERE m.specialty = :specialite")
    List<HistoriqueRendezVous> findByMedecinSpecialite(@Param("specialite") String specialite);

}