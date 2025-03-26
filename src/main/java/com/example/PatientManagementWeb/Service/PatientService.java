package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.Entity.Patient;
import com.example.PatientManagementWeb.IService.IPatientService;
import com.example.PatientManagementWeb.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatientById(UUID id) {
        return patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found with id:"+ id));
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        UUID id = patient.getId();
        if(id == null || !patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id:" + id);
        }
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
