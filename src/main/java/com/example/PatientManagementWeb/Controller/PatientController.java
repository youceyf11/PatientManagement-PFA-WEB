package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.Entity.Patient;
import com.example.PatientManagementWeb.IService.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.findAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable UUID id) {
        return patientService.findPatientById(id);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
    }

}
