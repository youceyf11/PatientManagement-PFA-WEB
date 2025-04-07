package com.example.PatientManagementWeb.IService;

import com.example.PatientManagementWeb.DTO.HistoriqueRendezVousDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IHistoriqueService {
    List<HistoriqueRendezVousDTO> getHistoriqueByPatient(String patientId);
    List<HistoriqueRendezVousDTO> getHistoriqueByMedecin(String medecinId);
    List<HistoriqueRendezVousDTO> getHistoriqueByDateRange(LocalDateTime debut, LocalDateTime fin);
    List<HistoriqueRendezVousDTO> getHistoriqueByStatut(String statut);

    void saveRendezVousToHistorique(String rendezVousId);
    void updateHistorique(String id, HistoriqueRendezVousDTO historiqueDTO);
    void deleteHistorique(String id);

    List<HistoriqueRendezVousDTO> findByMedecinSpecialite(String specialite);

}
