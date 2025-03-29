package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Exceptions.UserNotFoundException;
import com.example.PatientManagementWeb.IService.IMedecinService;
import com.example.PatientManagementWeb.Repository.MedecinRepository;
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


    @Override
    public List<MedecinDTO> getAllMedecins() {
        return medecinRepository.findAll()
                .stream().map(medecin -> toDTO(medecin)).collect(Collectors.toList());
    }

    @Override
    public MedecinDTO getMedecin(String id) {
        return medecinRepository.findById(UUID.fromString(id))
                .map(medecin -> toDTO(medecin)).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void deleteMedecin(String id) {
        if(!medecinRepository.existsById(UUID.fromString(id))){
            throw new UserNotFoundException("User not found");
        }
        medecinRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void updateMedecin(MedecinDTO medecinDTO, String id) {
        Medecin medecin= medecinRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("Medecin not found"));
        medecin.setFirstName(medecinDTO.getFirstName());
        medecin.setLastName(medecinDTO.getLastName());
        medecin.setPhone(medecinDTO.getPhone());
        medecin.setSpecialty(medecinDTO.getSpecialty());
        medecin.setLocation( medecinDTO.getLocation());
        medecinRepository.save(medecin);
    }

    @Override
    public void createMedecin(MedecinDTO medecinDTO) {
        Medecin medecin = Medecin.builder()
                .username(medecinDTO.getUsername())
                .password(passwordEncoder.encode(medecinDTO.getPassword()))
                .firstName(medecinDTO.getFirstName())
                .lastName(medecinDTO.getLastName())
                .phone(medecinDTO.getPhone())
                .specialty(medecinDTO.getSpecialty())
                .location(medecinDTO.getLocation())
                .build();
        medecinRepository.save(medecin);
    }

    private MedecinDTO toDTO (Medecin medecin) {
        return MedecinDTO.builder()
                .id(medecin.getId().toString())
                .username(medecin.getUsername())
                .password(medecin.getPassword())
                .firstName(medecin.getFirstName())
                .lastName(medecin.getLastName())
                .phone(medecin.getPhone())
                .createdAt(medecin.getCreatedAt().toString())
                .updatedAt(medecin.getUpdatedAt().toString())
                .specialty(medecin.getSpecialty())
                .location(medecin.getLocation())
                .build();
    }
}
