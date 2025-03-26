package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.IService.IMedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medecins")
public class MedecinController {

    @Autowired
    private IMedecinService medecinService;

    @GetMapping
    public List<Medecin> getAllMedecins() {
        return medecinService.getAllMedecins();
    }

    @GetMapping("/{id}")
    public Medecin getMedecinById(@PathVariable UUID id) {
        return medecinService.findMedecinById(id);
    }

    @PostMapping
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinService.createMedecin(medecin);
    }

    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable UUID id) {
        medecinService.deleteMedecin(id);
    }

    @PutMapping
    public Medecin updateMedecin(@RequestBody Medecin medecin) {
        return medecinService.updateMedecin(medecin);
    }

}
