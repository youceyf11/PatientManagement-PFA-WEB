package com.example.PatientManagementWeb.IService;

import com.example.PatientManagementWeb.Entity.Medecin;

import java.util.List;
import java.util.UUID;

public interface IMedecinService {

    List<Medecin> getAllMedecins();
    Medecin createMedecin(Medecin medecin);
    Medecin findMedecinById(UUID id);
    Medecin updateMedecin(Medecin medecin);
    void deleteMedecin(UUID id);
}
