package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.IService.IMedecinService;
import com.example.PatientManagementWeb.Repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedecinService implements IMedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin createMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin findMedecinById(UUID id) {
        return medecinRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Medecin not found with id:" + id));
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        UUID id = medecin.getId();
        if(id == null || !medecinRepository.existsById(id)) {
            throw new RuntimeException("Medecin not found with id:" + id);
        }
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecin(UUID id) {
        medecinRepository.deleteById(id);
    }


}
