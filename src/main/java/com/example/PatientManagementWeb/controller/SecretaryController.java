package com.example.PatientManagementWeb.controller;

import com.example.PatientManagementWeb.DTO.ApiResponse;
import com.example.PatientManagementWeb.DTO.SecretaryDTO;
import com.example.PatientManagementWeb.Enum.ResponseCode;
import com.example.PatientManagementWeb.service.SecretaryService;
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
@RequiredArgsConstructor
@RequestMapping("/api/secretaries")
public class SecretaryController {

    private final SecretaryService secretaryService;
    private final I18nMessageUtil i18nMessageUtil;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @GetMapping
    public ResponseEntity<ApiResponse<List<SecretaryDTO>>> getAllSecretaries() {
        List<SecretaryDTO> secretaries = secretaryService.getAllSecretaries();
        ApiResponse<List<SecretaryDTO>> response = ApiResponse.success(
                secretaries,
                "Liste des secrétaires récupérée avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        System.out.println("Liste des secrétaires récupérée le : " + dateTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SecretaryDTO>> getSecretary(@PathVariable String id) {
        SecretaryDTO secretary = secretaryService.getSecretary(id);
        ApiResponse<SecretaryDTO> response = ApiResponse.success(
                secretary,
                "Secrétaire récupéré avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        System.out.println("Détail du secrétaire " + id + " récupéré à " + dateTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createSecretary(@RequestBody @Valid SecretaryDTO secretaryDTO) {
        try {
            String secretaryId = secretaryService.createSecretary(secretaryDTO);
            ApiResponse<String> response = ApiResponse.success(
                    secretaryId,
                    "Secrétaire créé avec succès",
                    HttpStatus.CREATED
            );

            LocalDateTime creationTime = response.getLocalDateTime();
            System.out.println("Nouveau secrétaire créé avec ID " + secretaryId + " le " + creationTime.format(formatter));

            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            ApiResponse<String> response = i18nMessageUtil.createResponse(ResponseCode.SERVER_ERROR, null);

            System.out.println("Erreur survenue le " + response.getLocalDateTime().format(formatter));
            System.out.println("Message d'erreur: " + e.getMessage());

            return ResponseEntity.status(response.getStatus()).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSecretary(@PathVariable String id) {
        secretaryService.deleteSecretary(id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Secrétaire supprimé avec succès",
                HttpStatus.NO_CONTENT
        );

        System.out.println("Suppression du secrétaire " + id + " effectuée le " +
                response.getLocalDateTime().format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateSecretary(@PathVariable String id, @RequestBody @Valid SecretaryDTO secretaryDTO) {
        secretaryService.updateSecretary(secretaryDTO, id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Secrétaire mis à jour avec succès",
                HttpStatus.NO_CONTENT
        );

        LocalDateTime updateTime = response.getLocalDateTime();
        System.out.println("Mise à jour du secrétaire " + id + " effectuée le " + updateTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}