package com.example.PatientManagementWeb.IService;


import com.example.PatientManagementWeb.Entity.Patient;
import com.example.PatientManagementWeb.Entity.Secretary;

import java.util.List;
import java.util.UUID;

public interface IPatientService {

    List<Patient> findAllPatients();
    Patient findPatientById(UUID id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatient(UUID id);
}
