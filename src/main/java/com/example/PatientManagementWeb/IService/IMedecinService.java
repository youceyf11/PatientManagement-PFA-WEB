package com.example.PatientManagementWeb.IService;


import com.example.PatientManagementWeb.DTO.MedecinDTO;

import java.util.List;

public interface IMedecinService {
        List<MedecinDTO> getAllMedecins();
        MedecinDTO getMedecin(String id);
        void deleteMedecin(String id);
        void updateMedecin(MedecinDTO medecinDTO, String id);
        void createMedecin(MedecinDTO medecinDTO);

}
