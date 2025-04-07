package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.DTO.RendezVousDTO;
import com.example.PatientManagementWeb.Service.RendezVousService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
public class RendezVousController {
    private final RendezVousService rendezVousService;

    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        return new ResponseEntity<>(rendezVousService.getAllRendezVous(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDTO> getRendezVous(@PathVariable String id) {
        return new ResponseEntity<>(rendezVousService.getRendezVous(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createRendezVous(@RequestBody @Valid RendezVousDTO rendezVousDTO) {
        rendezVousService.createRendezVous(rendezVousDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRendezVous(@RequestBody @Valid RendezVousDTO rendezVousDTO, @PathVariable String id) {
        rendezVousService.updateRendezVous(rendezVousDTO,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable String id) {
        rendezVousService.deleteRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{patientId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatient(@PathVariable String patientId) {
        rendezVousService.getRendezVousByPatient(patientId);
        return new ResponseEntity<>(rendezVousService.getAllRendezVous(), HttpStatus.OK);
    }

    @GetMapping("/find/{medecineId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByMedecin(@PathVariable String medecineId) {
        rendezVousService.getRendezVousByMedecin(medecineId);
        return new ResponseEntity<>(rendezVousService.getAllRendezVous(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RendezVousDTO>> findRendezVous(@RequestParam String medecineId, @RequestParam LocalDateTime dateTime) {
        rendezVousService.findRendezVous(medecineId, dateTime);
        return new ResponseEntity<>(rendezVousService.getAllRendezVous(), HttpStatus.OK);
    }

    @PutMapping("/annuler/{id}")
    public ResponseEntity<Void> annulerRendezVous(@PathVariable String id) {
        rendezVousService.annulerRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
