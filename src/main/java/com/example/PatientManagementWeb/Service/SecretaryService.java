package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;
import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Entity.Secretary;
import com.example.PatientManagementWeb.Enum.ErrorCode;
import com.example.PatientManagementWeb.Exceptions.ProfessionalException;
import com.example.PatientManagementWeb.Exceptions.TechnicalException;
import com.example.PatientManagementWeb.IService.ISecretaryService;
import com.example.PatientManagementWeb.Repository.MedecinRepository;
import com.example.PatientManagementWeb.Repository.SecretaryRepository;
import com.example.PatientManagementWeb.mapper.SecretaryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecretaryService implements ISecretaryService {

    private final SecretaryRepository secretaryRepository;
    private final PasswordEncoder passwordEncoder;
    private final MedecinRepository medecinRepository;
    private final SecretaryMapper secretaryMapper;


    @Override
    public List<SecretaryDTO> getAllSecretaries() {
       try{
           return secretaryRepository.findAll()
                   .stream().map(secretaryMapper::toDTO).collect(Collectors.toList());
       }catch (Exception e){
           throw new TechnicalException(ErrorCode.DATABASE_ERROR);
       }
    }

    @Override
    public SecretaryDTO getSecretary(String id) {
        try {
            return secretaryRepository.findById(UUID.fromString(id))
                    .map(secretaryMapper::toDTO)
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.SECRETARY_NOT_FOUND));
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public void createSecretary(SecretaryDTO secretaryDTO) {
        Medecin medecin= medecinRepository.findById(UUID.fromString(secretaryDTO.getMedecinId()))
                .orElseThrow(()-> new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND));
        /*if(secretaryRepository.findByMedecin(medecin)){
            throw new ProfessionalException(ErrorCode.SECRETARY_ALREADY_EXIST);
        } */
        try {
            Secretary secretary = secretaryMapper.toEntity(secretaryDTO);
            secretary.setPassword(passwordEncoder.encode(secretary.getPassword()));
            secretaryRepository.save(secretary);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateSecretary(SecretaryDTO secretaryDTO, String id) {
        Secretary secretary= secretaryRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProfessionalException(ErrorCode.SECRETARY_NOT_FOUND));
        try {
            secretaryMapper.updateSecretaryFromDTO(secretaryDTO, secretary);
            secretaryRepository.save(secretary);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteSecretary(String id) {
        if(!secretaryRepository.existsById(UUID.fromString(id))){
            throw new ProfessionalException(ErrorCode.SECRETARY_NOT_FOUND);
        }
        try {
            secretaryRepository.deleteById(UUID.fromString(id));
        }catch(Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }


}
