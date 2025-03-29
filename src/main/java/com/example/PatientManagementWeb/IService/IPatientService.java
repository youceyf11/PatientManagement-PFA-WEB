package com.example.PatientManagementWeb.IService;


import com.example.PatientManagementWeb.DTO.PatientDTO;
import com.example.PatientManagementWeb.Entity.Patient;
import com.example.PatientManagementWeb.Entity.Secretary;

import java.util.List;
import java.util.UUID;

public interface IPatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO getPatient(String id);
    void deletePatient(String id);
    void updatePatient(PatientDTO patientDTO, String id);
    void createPatient(PatientDTO patientDTO);

}
