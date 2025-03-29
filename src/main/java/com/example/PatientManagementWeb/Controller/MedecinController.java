package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.Service.MedecinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medecins")
public class MedecinController {

    private final MedecinService medecinService;

    @GetMapping
    public ResponseEntity<List<MedecinDTO>> getAllMedecins() {
        return new ResponseEntity<>(medecinService.getAllMedecins(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedecinDTO> getMedecin(@PathVariable String id) {
        return new ResponseEntity<>(medecinService.getMedecin(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable String id) {
        medecinService.deleteMedecin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedecin(@PathVariable String id, @RequestBody @Valid MedecinDTO medecinDTO) {
        medecinService.updateMedecin(medecinDTO, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Void> createMedecin(@RequestBody @Valid MedecinDTO medecinDTO) {
        medecinService.createMedecin(medecinDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
