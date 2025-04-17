package com.example.PatientManagementWeb.service;

import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.entity.Medecin;
import com.example.PatientManagementWeb.Enum.ErrorCode;
import com.example.PatientManagementWeb.exceptions.ProfessionalException;
import com.example.PatientManagementWeb.exceptions.TechnicalException;
import com.example.PatientManagementWeb.iservice.IMedecinService;
import com.example.PatientManagementWeb.repository.MedecinRepository;
import com.example.PatientManagementWeb.mapper.MedecinMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedecinService implements IMedecinService {

    private final MedecinRepository medecinRepository;
    private final PasswordEncoder passwordEncoder;
    private final MedecinMapper medecinMapper;


    @Override
    public List<MedecinDTO> getAllMedecins() {
        try {
            return medecinRepository.findAll()
                    .stream().map(medecinMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public MedecinDTO getMedecin(String id) {
        Medecin medecin = medecinRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND));
        try {
            return medecinRepository.findById(UUID.fromString(id))
                    .map(medecinMapper::toDTO)
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND, "ID:" + id));
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteMedecin(String id) {
        if(!medecinRepository.existsById(UUID.fromString(id))){
            throw new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND,"ID:"+ id);
        }
        try {
            medecinRepository.deleteById(UUID.fromString(id));
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateMedecin(MedecinDTO medecinDTO, String id) {
        Medecin medecin= medecinRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND,"ID:"+ id));
        try {
            medecinMapper.updateMedecinFromDTO(medecinDTO, medecin);
            medecinRepository.save(medecin);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public String createMedecin(MedecinDTO medecinDTO) {
        try {
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        medecin.setPassword(passwordEncoder.encode(medecin.getPassword()));
        Medecin savedMedecin = medecinRepository.save(medecin);
        return savedMedecin.getId().toString();
        }catch (Exception e){
        throw new TechnicalException(ErrorCode.DATABASE_ERROR);}
    }

    @Override
    public List<MedecinDTO> findBySpecialty(String specialty) {
        try {
            List<Medecin> medecins= medecinRepository.findBySpecialty(specialty);
                    return medecins.stream().map(medecinMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

}
