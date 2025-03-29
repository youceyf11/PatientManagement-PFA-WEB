package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;
import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Entity.Secretary;
import com.example.PatientManagementWeb.Exceptions.UserNotFoundException;
import com.example.PatientManagementWeb.IService.ISecretaryService;
import com.example.PatientManagementWeb.Repository.MedecinRepository;
import com.example.PatientManagementWeb.Repository.SecretaryRepository;
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


    @Override
    public List<SecretaryDTO> getAllSecretaries() {
        return secretaryRepository.findAll()
                .stream().map(secretary -> toDTO(secretary)).collect(Collectors.toList());
    }

    @Override
    public SecretaryDTO getSecretary(String id) {
        return secretaryRepository.findById(UUID.fromString(id))
                .map(secretary -> toDTO(secretary))
                .orElseThrow(() -> new UserNotFoundException("Secretary not found"));

    }

    @Override
    public void createSecretary(SecretaryDTO secretaryDTO) {
        Medecin medecin= medecinRepository.findById(UUID.fromString(secretaryDTO.getMedecinId()))
                .orElseThrow(()-> new UserNotFoundException("Medecin not found"));

        Secretary secretary= Secretary.builder()
                .username(secretaryDTO.getUsername())
                .password(passwordEncoder.encode(secretaryDTO.getPassword()))
                .firstName(secretaryDTO.getFirstName())
                .lastName(secretaryDTO.getLastName())
                .phone(secretaryDTO.getPhone())
                .medecin(medecin)
                .departament(secretaryDTO.getDepartament())
                .build();
        secretaryRepository.save(secretary);
    }

    @Override
    public void updateSecretary(SecretaryDTO secretaryDTO, String id) {
        Secretary secretary= secretaryRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("Secretary not found"));
        secretary.setFirstName(secretaryDTO.getFirstName());
        secretary.setLastName(secretaryDTO.getLastName());
        secretary.setPhone(secretaryDTO.getPhone());
        secretary.setDepartament(secretaryDTO.getDepartament());
        secretaryRepository.save(secretary);
    }

    @Override
    public void deleteSecretary(String id) {
        if(!secretaryRepository.existsById(UUID.fromString(id))){
            throw new UserNotFoundException("Secretary not found");
        }
        secretaryRepository.deleteById(UUID.fromString(id));
    }


    private SecretaryDTO toDTO(Secretary secretary) {
        return SecretaryDTO.builder()
                .id(secretary.getId().toString())
                .username(secretary.getUsername())
                .password(passwordEncoder.encode(secretary.getPassword()))
                .firstName(secretary.getFirstName())
                .lastName(secretary.getLastName())
                .phone(secretary.getPhone())
                .createdAt(secretary.getCreatedAt().toString())
                .updatedAt(secretary.getUpdatedAt().toString())
                .medecinId(secretary.getMedecin().getId().toString())
                .departament(secretary.getDepartament())
                .build();
    }
}
