package com.example.PatientManagementWeb.service;

import com.example.PatientManagementWeb.DTO.RendezVousDTO;
import com.example.PatientManagementWeb.entity.RendezVous;
import com.example.PatientManagementWeb.Enum.ErrorCode;
import com.example.PatientManagementWeb.exceptions.ProfessionalException;
import com.example.PatientManagementWeb.exceptions.TechnicalException;
import com.example.PatientManagementWeb.iservice.IRendezVousService;
import com.example.PatientManagementWeb.repository.PatientRepository;
import com.example.PatientManagementWeb.repository.RendezVousRepository;
import com.example.PatientManagementWeb.Enum.Statut;
import com.example.PatientManagementWeb.mapper.RendezVousMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService implements IRendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;
    private final RendezVousMapper rendezVousMapper;

    @Override
    public List<RendezVousDTO> getAllRendezVous() {
        try {
            return rendezVousRepository.findAll()
                    .stream().map(rendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }

    }

    @Override
    public RendezVousDTO getRendezVous(String id) {
        return rendezVousRepository.findById(UUID.fromString(id))
                .map(rendezVousMapper::toDTO)
                .orElseThrow(() -> new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND));

    }

    @Override
    public String createRendezVous(RendezVousDTO rendezVousDTO) {
         /*Patient patient = patientRepository.findById(UUID.fromString(rendezVousDTO.getPatientId()))
                .orElseThrow(()-> new ProfessionalException(ErrorCode.PATIENT_NOT_FOUND)); */
        try {
            RendezVous rendezVous = rendezVousMapper.toEntity(rendezVousDTO);
            RendezVous savedRendezVous=rendezVousRepository.save(rendezVous);
            return savedRendezVous.getId().toString();
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateRendezVous(RendezVousDTO rendezVousDTO, String id) {
        RendezVous rendezVous= rendezVousRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND));

        /*Patient patient= patientRepository.findById(UUID.fromString(rendezVousDTO.getPatientId()))
                .orElseThrow(()-> new ProfessionalException(ErrorCode.PATIENT_NOT_FOUND)); */
        try {
            rendezVousMapper.updateRendezVousFromDTO(rendezVousDTO, rendezVous);
            rendezVousRepository.save(rendezVous);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteRendezVous(String id) {
        if(!rendezVousRepository.existsById(UUID.fromString(id))){
            throw new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }
        rendezVousRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<RendezVousDTO> getRendezVousByPatient(String patientId) {
        try {
            return rendezVousRepository.findByPatientId(UUID.fromString(patientId))
                    .stream().map(rendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public List<RendezVousDTO> getRendezVousByMedecin(String medicineId) {
        try {
            return rendezVousRepository.findByMedecinId(UUID.fromString(medicineId))
                    .stream().map(rendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }

    }

    @Override
    public List<RendezVousDTO> findRendezVous(String medecinId, LocalDateTime dateTime) {
        try {
            return rendezVousRepository.findByMedecinIdAndDateHeure(UUID.fromString(medecinId),dateTime)
                    .stream().map(rendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }

    }

    @Override
    @Transactional
    public void annulerRendezVous(String id) {
        try {
            RendezVous rendezVous = rendezVousRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND));
            if (rendezVous.getStatut() == Statut.CANCELLED) {
                throw new ProfessionalException(ErrorCode.APPOINTEMENT_ALREADY_CANCELED);
            }
            rendezVous.setStatut(Statut.CANCELLED);
            rendezVousRepository.save(rendezVous);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

}
