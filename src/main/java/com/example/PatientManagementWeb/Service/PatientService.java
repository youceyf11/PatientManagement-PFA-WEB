package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.DTO.PatientDTO;
import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Entity.Patient;
import com.example.PatientManagementWeb.Exceptions.UserNotFoundException;
import com.example.PatientManagementWeb.IService.IPatientService;
import com.example.PatientManagementWeb.Repository.MedecinRepository;
import com.example.PatientManagementWeb.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream().map(patient -> toDTO(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatient(String id) {
        return patientRepository.findById(UUID.fromString(id))
                .map(patient -> toDTO(patient)).orElse(null);
    }

    @Override
    public void deletePatient(String id) {
        if(!patientRepository.existsById(UUID.fromString(id))){
            throw new UserNotFoundException("patient not found");
        }
            patientRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void updatePatient(PatientDTO patientDTO, String id) {
        Patient patient= patientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("patient not found"));
        Medecin medecin= medecinRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("medecin not found"));
        patient.setMedecin(medecin);
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setPhone(patientDTO.getPhone());
        patient.setAge(patientDTO.getAge());
        patient.setGender(patientDTO.getGender());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        patientRepository.save(patient);

    }

    @Override
    public void createPatient(PatientDTO patientDTO) {
        Patient patient= Patient.builder()
                .username(patientDTO.getUsername())
                .password(passwordEncoder.encode(patientDTO.getPassword()))
                .firstName(patientDTO.getFirstName())
                .lastName(patientDTO.getLastName())
                .phone(patientDTO.getPhone())
                .age(patientDTO.getAge())
                .gender(patientDTO.getGender())
                .medicalHistory(patientDTO.getMedicalHistory())
                .build();
        patientRepository.save(patient);
    }

    private PatientDTO toDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId().toString())
                .username(patient.getUsername())
                .password(passwordEncoder.encode(patient.getPassword()))
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phone(patient.getPhone())
                .createdAt(patient.getCreatedAt().toString())
                .updatedAt(patient.getUpdatedAt().toString())
                .age(patient.getAge())
                .gender(patient.getGender())
                .adresse(patient.getAddress())
                .medicalHistory(patient.getMedicalHistory())
                .medecinId(patient.getMedecin().getId().toString())
                .build();
    }
}
