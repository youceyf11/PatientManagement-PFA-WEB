package com.example.PatientManagementWeb.service;

import com.example.PatientManagementWeb.DTO.PatientDTO;
import com.example.PatientManagementWeb.entity.Patient;
import com.example.PatientManagementWeb.Enum.ErrorCode;
import com.example.PatientManagementWeb.exceptions.ProfessionalException;
import com.example.PatientManagementWeb.exceptions.TechnicalException;
import com.example.PatientManagementWeb.iservice.IPatientService;
import com.example.PatientManagementWeb.repository.MedecinRepository;
import com.example.PatientManagementWeb.repository.PatientRepository;
import com.example.PatientManagementWeb.mapper.PatientMapper;
import jakarta.transaction.Transactional;
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
    private final PatientMapper patientMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        try {
            return patientRepository.findAll()
                    .stream().map(patientMapper::toDTO).collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public PatientDTO getPatient(String id) {
        try {
            return patientRepository.findById(UUID.fromString(id))
                    .map(patientMapper::toDTO)
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.PATIENT_NOT_FOUND));
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void deletePatient(String id) {
        if (!patientRepository.existsById(UUID.fromString(id))) {
            throw new ProfessionalException(ErrorCode.PATIENT_NOT_FOUND);
        }
        try {
            patientRepository.deleteById(UUID.fromString(id));
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updatePatient(PatientDTO patientDTO, String id) {
        Patient patient = patientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProfessionalException(ErrorCode.PATIENT_NOT_FOUND));

        if (patientDTO.getMedecinId() != null) {
            medecinRepository.findById(UUID.fromString(patientDTO.getMedecinId()))
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND));
        }
        try {
            patientMapper.updatePatientFromDTO(patientDTO, patient);
            patientRepository.save(patient);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public String createPatient(PatientDTO patientDTO) {
        if (patientRepository.existsByUsername(patientDTO.getUsername())) {
            throw new ProfessionalException(ErrorCode.PATIENT_ALREADY_EXISTS);
        }
        try {
            Patient patient = patientMapper.toEntity(patientDTO);
            patient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
            Patient savedPatient = patientRepository.save(patient);
            return savedPatient.getId().toString();
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }


}
