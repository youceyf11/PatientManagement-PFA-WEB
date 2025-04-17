package com.example.PatientManagementWeb.iservice;


import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.entity.Medecin;

import java.util.List;

public interface IMedecinService {
        List<MedecinDTO> getAllMedecins();
        MedecinDTO getMedecin(String id);
        void deleteMedecin(String id);
        void updateMedecin(MedecinDTO medecinDTO, String id);
        String createMedecin(MedecinDTO medecinDTO);

        List<MedecinDTO> findBySpecialty(String specialty);
}
