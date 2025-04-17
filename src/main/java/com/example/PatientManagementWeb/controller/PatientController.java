package com.example.PatientManagementWeb.controller;

import com.example.PatientManagementWeb.DTO.ApiResponse;
import com.example.PatientManagementWeb.DTO.PatientDTO;
import com.example.PatientManagementWeb.Enum.ResponseCode;
import com.example.PatientManagementWeb.service.PatientService;
import com.example.PatientManagementWeb.exceptionHandler.I18nMessageUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final I18nMessageUtil i18nMessageUtil;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientDTO>>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        ApiResponse<List<PatientDTO>> response = ApiResponse.success(
                patients,
                "Liste des patients récupérée avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        String formattedDate = dateTime.format(formatter);
        System.out.println("Liste des patients récupérée le : " + formattedDate);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientById(@PathVariable String id) {
        PatientDTO patient = patientService.getPatient(id);
        ApiResponse<PatientDTO> response = ApiResponse.success(
                patient,
                "Patient récupéré avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        System.out.println("Détail du patient " + id + " récupéré à " + dateTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Patient supprimé avec succès",
                HttpStatus.NO_CONTENT
        );

        System.out.println("Suppression du patient " + id + " effectuée le " +
                response.getLocalDateTime().format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePatient(@PathVariable String id, @RequestBody @Valid PatientDTO patientDTO) {
        patientService.updatePatient(patientDTO, id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Patient mis à jour avec succès",
                HttpStatus.NO_CONTENT
        );

        LocalDateTime updateTime = response.getLocalDateTime();
        System.out.println("Mise à jour du patient " + id + " effectuée le " + updateTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        try {
            String patientId = patientService.createPatient(patientDTO);
            ApiResponse<String> response = ApiResponse.success(
                    patientId,
                    "Patient créé avec succès",
                    HttpStatus.CREATED
            );

            LocalDateTime creationTime = response.getLocalDateTime();
            System.out.println("Nouveau patient créé avec ID " + patientId + " le " + creationTime.format(formatter));

            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            ApiResponse<String> response = i18nMessageUtil.createResponse(ResponseCode.SERVER_ERROR, null);

            System.out.println("Erreur survenue le " + response.getLocalDateTime().format(formatter));
            System.out.println("Message d'erreur: " + e.getMessage());

            return ResponseEntity.status(response.getStatus()).body(response);
        }
    }
}