package com.example.PatientManagementWeb.iservice;


import com.example.PatientManagementWeb.DTO.PatientDTO;

import java.util.List;

public interface IPatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO getPatient(String id);
    void deletePatient(String id);
    void updatePatient(PatientDTO patientDTO, String id);
    String createPatient(PatientDTO patientDTO);

}
