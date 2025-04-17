package com.example.PatientManagementWeb.controller;

import com.example.PatientManagementWeb.DTO.ApiResponse;
import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.Enum.ResponseCode;
import com.example.PatientManagementWeb.exceptionHandler.I18nMessageUtil;
import com.example.PatientManagementWeb.service.MedecinService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    private final MedecinService medecinService;
    private final I18nMessageUtil i18nMessageUtil;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public MedecinController(MedecinService medecinService, I18nMessageUtil i18nMessageUtil) {
        this.medecinService = medecinService;
        this.i18nMessageUtil = i18nMessageUtil;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MedecinDTO>>> getAllMedecins() {
        List<MedecinDTO> medecins = medecinService.getAllMedecins();
        ApiResponse<List<MedecinDTO>> response = ApiResponse.success(
                medecins,
                "Liste des médecins récupérée avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        String formattedDate = dateTime.format(formatter);
        System.out.println("Liste des médecins récupérée le : " + formattedDate);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedecinDTO>> getMedecin(@PathVariable String id) {
        MedecinDTO medecin = medecinService.getMedecin(id);
        ApiResponse<MedecinDTO> response = ApiResponse.success(
                medecin,
                "Médecin récupéré avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        System.out.println("Détail du médecin " + id + " récupéré à " + dateTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMedecin(@PathVariable String id) {
        medecinService.deleteMedecin(id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Médecin supprimé avec succès",
                HttpStatus.NO_CONTENT
        );

        System.out.println("Suppression du médecin " + id + " effectuée le " +
                response.getLocalDateTime().format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateMedecin(@PathVariable String id, @RequestBody @Valid MedecinDTO medecinDTO) {
        medecinService.updateMedecin(medecinDTO, id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Médecin mis à jour avec succès",
                HttpStatus.NO_CONTENT
        );

        LocalDateTime updateTime = response.getLocalDateTime();
        System.out.println("Mise à jour du médecin " + id + " effectuée le " + updateTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createMedecin(@RequestBody @Valid MedecinDTO medecinDTO, HttpServletResponse httpServletResponse) {
        try {
            String medecinId = medecinService.createMedecin(medecinDTO);
            ApiResponse<String> response = ApiResponse.success(
                    medecinId,
                    "Médecin créé avec succès",
                    HttpStatus.CREATED
            );

            LocalDateTime creationTime = response.getLocalDateTime();
            System.out.println("Nouveau médecin créé avec ID " + medecinId + " le " + creationTime.format(formatter));

            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            ApiResponse<String> response = i18nMessageUtil.createResponse(ResponseCode.SERVER_ERROR, null);

            System.out.println("Erreur survenue le " + response.getLocalDateTime().format(formatter));
            System.out.println("Message d'erreur: " + e.getMessage());

            return ResponseEntity.status(response.getStatus()).body(response);
        }
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<ApiResponse<List<MedecinDTO>>> findBySpecialty(@PathVariable String specialty) {
        List<MedecinDTO> medecins = medecinService.findBySpecialty(specialty);
        ApiResponse<List<MedecinDTO>> response = ApiResponse.success(
                medecins,
                "Médecins de spécialité " + specialty + " récupérés avec succès",
                HttpStatus.OK
        );

        LocalDateTime searchTime = response.getLocalDateTime();
        System.out.println("Recherche des médecins de spécialité " + specialty + " effectuée le " +
                searchTime.format(formatter));

        return ResponseEntity.ok(response);
    }
}