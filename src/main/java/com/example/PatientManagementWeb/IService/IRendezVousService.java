package com.example.PatientManagementWeb.IService;

import com.example.PatientManagementWeb.DTO.RendezVousDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IRendezVousService {
    List<RendezVousDTO> getAllRendezVous();
    RendezVousDTO getRendezVous(String id);
    void createRendezVous(RendezVousDTO rendezVousDTO);
    void updateRendezVous(RendezVousDTO rendezVousDTO, String id);
    void deleteRendezVous(String id);

    List<RendezVousDTO> getRendezVousByPatient(String patientId);
    List<RendezVousDTO> getRendezVousByMedecin(String medicineId);
    List<RendezVousDTO> findRendezVous(String medecinId, LocalDateTime dateTime);
    void annulerRendezVous(String id);

}
