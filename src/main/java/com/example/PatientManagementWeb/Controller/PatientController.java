package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.DTO.PatientDTO;
import com.example.PatientManagementWeb.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable String id) {
        return new ResponseEntity<>(patientService.getPatient(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        this.patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatient(@PathVariable String id, @RequestBody @Valid PatientDTO patientDTO) {
        patientService.updatePatient(patientDTO,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Void> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        patientService.createPatient(patientDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

}
