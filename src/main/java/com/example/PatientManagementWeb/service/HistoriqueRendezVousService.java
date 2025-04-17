package com.example.PatientManagementWeb.service;

import com.example.PatientManagementWeb.DTO.HistoriqueRendezVousDTO;
import com.example.PatientManagementWeb.entity.HistoriqueRendezVous;
import com.example.PatientManagementWeb.entity.Patient;
import com.example.PatientManagementWeb.entity.RendezVous;
import com.example.PatientManagementWeb.Enum.ErrorCode;
import com.example.PatientManagementWeb.Enum.Statut;
import com.example.PatientManagementWeb.exceptions.ProfessionalException;
import com.example.PatientManagementWeb.exceptions.TechnicalException;
import com.example.PatientManagementWeb.iservice.IHistoriqueService;
import com.example.PatientManagementWeb.repository.*;
import com.example.PatientManagementWeb.mapper.HistoriqueRendezVousMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HistoriqueRendezVousService implements IHistoriqueService {

    private final HistoriqueRendezVousRepository historiqueRendezVousRepository;
    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;
    //private final MedecinRepository medecinRepository;
    private final HistoriqueRendezVousMapper historiqueRendezVousMapper;


    @Override
    public List<HistoriqueRendezVousDTO> getHistoriqueByPatient(String patientId) {
        try {
            Patient patient = patientRepository.findById(UUID.fromString(patientId))
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.PATIENT_NOT_FOUND));

            List<RendezVous> rendezVousList = rendezVousRepository.findByPatientId(UUID.fromString(patientId));

            for (RendezVous rendezVous : rendezVousList) {
                if (!historiqueRendezVousRepository.existsById(rendezVous.getId().toString())) {
                    HistoriqueRendezVous historiqueRendezVous = new HistoriqueRendezVous();
                    historiqueRendezVous.setId(rendezVous.getId().toString());
                    historiqueRendezVous.setPatient(patient);
                    historiqueRendezVous.setMedecinId(rendezVous.getMedecin().getId().toString());
                    historiqueRendezVous.setDateTime(rendezVous.getDateHeure());
                    historiqueRendezVous.setStatut(rendezVous.getStatut().toString());
                    historiqueRendezVousRepository.save(historiqueRendezVous);

                }
            }
            return historiqueRendezVousRepository.findByPatient_Id(UUID.fromString(patientId))
                    .stream().map(historiqueRendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public List<HistoriqueRendezVousDTO> getHistoriqueByMedecin(String medecinId) {
        try {
            /*Medecin medecin = medecinRepository.findById(UUID.fromString(medecinId))
                    .orElseThrow(() -> new ProfessionalException(ErrorCode.MEDECIN_NOT_FOUND)); */

            return historiqueRendezVousRepository.findByMedecinId(medecinId)
                    .stream().map(historiqueRendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public List<HistoriqueRendezVousDTO> getHistoriqueByDateRange(LocalDateTime debut, LocalDateTime fin) {
        try {
            if (debut == null || fin == null) {
                throw new ProfessionalException(ErrorCode.INVALID_APPOINTMENT_TIME, "Les dates de début et de fin ne peuvent pas être nulles");
            }

            if (debut.isAfter(fin)) {
                throw new ProfessionalException(ErrorCode.PAST_APPOINTMENT_DATE, "La date de début doit être antérieure à la date de fin");
            }

            if (debut.plusYears(1).isBefore(fin)) {
                throw new ProfessionalException(ErrorCode.INVALID_APPOINTMENT_TIME, "La plage de dates ne peut pas dépasser un an");
            }
            return historiqueRendezVousRepository.findByDateTimeBetween(debut, fin)
                    .stream().map(historiqueRendezVousMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public List<HistoriqueRendezVousDTO> getHistoriqueByStatut(String statut) {
        List<RendezVous> rendezVousList= rendezVousRepository.findByStatut(Enum.valueOf(Statut.class, statut));

        if(rendezVousList.isEmpty()){
            throw new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }

        return historiqueRendezVousRepository.findByStatut(statut)
                .stream().map(historiqueRendezVousMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveRendezVousToHistorique(String rendezVousId) {
        try{
            RendezVous rendezVous= rendezVousRepository.findById(UUID.fromString(rendezVousId))
                    .orElseThrow(()-> new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND));

            if(historiqueRendezVousRepository.existsById(rendezVousId)){
                throw new ProfessionalException(ErrorCode.DUPLICATE_RECORD,"cet rendez vous exist");
            }
            HistoriqueRendezVous historiqueRendezVous= new HistoriqueRendezVous();
            historiqueRendezVous.setId(rendezVousId);
            historiqueRendezVous.setPatient(rendezVous.getPatient());
            historiqueRendezVous.setMedecinId(rendezVous.getMedecin().getId().toString());
            historiqueRendezVous.setDateTime(rendezVous.getDateHeure());
            historiqueRendezVous.setStatut(rendezVous.getStatut().toString());
            //return historiqueRendezVousMapper.toDTO(historiqueRendezVousRepository.save(historiqueRendezVous));
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateHistorique(String id, HistoriqueRendezVousDTO historiqueDTO) {
        HistoriqueRendezVous historiqueRendezVous= historiqueRendezVousRepository.findById(id)
                .orElseThrow(() -> new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND,"L'historique de vous n'existe pas"));

        try{
            historiqueRendezVousMapper.updateRendezVousFromDTO(historiqueDTO, historiqueRendezVous);
            historiqueRendezVousRepository.save(historiqueRendezVous);
        }catch (Exception e){
            throw new TechnicalException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteHistorique(String id) {
        if(!historiqueRendezVousRepository.existsById(id)){
            throw new ProfessionalException(ErrorCode.APPOINTMENT_NOT_FOUND,"L'historique de vous n'existe pas");
        }
        rendezVousRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<HistoriqueRendezVousDTO> findByMedecinSpecialite(String specialty) {
       //List<Medecin> medecin = medecinRepository.findByMedecinSpecialite(specialty);
       return historiqueRendezVousRepository.findByMedecinSpecialite(specialty)
               .stream()
               .map(historiqueRendezVousMapper::toDTO)
               .collect(Collectors.toList());
    }

}
