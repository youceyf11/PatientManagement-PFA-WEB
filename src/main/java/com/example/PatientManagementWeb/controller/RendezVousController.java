package com.example.PatientManagementWeb.controller;

import com.example.PatientManagementWeb.DTO.ApiResponse;
import com.example.PatientManagementWeb.DTO.RendezVousDTO;
import com.example.PatientManagementWeb.Enum.ResponseCode;
import com.example.PatientManagementWeb.service.RendezVousService;
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
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
public class RendezVousController {
    private final RendezVousService rendezVousService;
    private final I18nMessageUtil i18nMessageUtil;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @GetMapping
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getAllRendezVous() {
        List<RendezVousDTO> rendezVous = rendezVousService.getAllRendezVous();
        ApiResponse<List<RendezVousDTO>> response = ApiResponse.success(
                rendezVous,
                "Liste des rendez-vous récupérée avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        System.out.println("Liste des rendez-vous récupérée le : " + dateTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> getRendezVous(@PathVariable String id) {
        RendezVousDTO rendezVous = rendezVousService.getRendezVous(id);
        ApiResponse<RendezVousDTO> response = ApiResponse.success(
                rendezVous,
                "Rendez-vous récupéré avec succès",
                HttpStatus.OK
        );

        LocalDateTime dateTime = response.getLocalDateTime();
        System.out.println("Détail du rendez-vous " + id + " récupéré à " + dateTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createRendezVous(@RequestBody RendezVousDTO rendezVousDTO) {
        try {
            String rendezVousId = rendezVousService.createRendezVous(rendezVousDTO);
            ApiResponse<String> response = ApiResponse.success(
                    rendezVousId,
                    "Rendez-vous créé avec succès",
                    HttpStatus.CREATED
            );

            LocalDateTime creationTime = response.getLocalDateTime();
            System.out.println("Nouveau rendez-vous créé avec ID " + rendezVousId + " le " + creationTime.format(formatter));

            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            ApiResponse<String> response = i18nMessageUtil.createResponse(ResponseCode.SERVER_ERROR, null);

            System.out.println("Erreur survenue le " + response.getLocalDateTime().format(formatter));
            System.out.println("Message d'erreur: " + e.getMessage());

            return ResponseEntity.status(response.getStatus()).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateRendezVous(@RequestBody @Valid RendezVousDTO rendezVousDTO, @PathVariable String id) {
        rendezVousService.updateRendezVous(rendezVousDTO, id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Rendez-vous mis à jour avec succès",
                HttpStatus.NO_CONTENT
        );

        LocalDateTime updateTime = response.getLocalDateTime();
        System.out.println("Mise à jour du rendez-vous " + id + " effectuée le " + updateTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRendezVous(@PathVariable String id) {
        rendezVousService.deleteRendezVous(id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Rendez-vous supprimé avec succès",
                HttpStatus.NO_CONTENT
        );

        System.out.println("Suppression du rendez-vous " + id + " effectuée le " +
                response.getLocalDateTime().format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{patientId}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getRendezVousByPatient(@PathVariable String patientId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByPatient(patientId);
        ApiResponse<List<RendezVousDTO>> response = ApiResponse.success(
                rendezVous,
                "Rendez-vous du patient récupérés avec succès",
                HttpStatus.OK
        );

        LocalDateTime searchTime = response.getLocalDateTime();
        System.out.println("Recherche des rendez-vous du patient " + patientId + " effectuée le " +
                searchTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/medecin/{medecinId}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getRendezVousByMedecin(@PathVariable String medecinId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByMedecin(medecinId);
        ApiResponse<List<RendezVousDTO>> response = ApiResponse.success(
                rendezVous,
                "Rendez-vous du médecin récupérés avec succès",
                HttpStatus.OK
        );

        LocalDateTime searchTime = response.getLocalDateTime();
        System.out.println("Recherche des rendez-vous du médecin " + medecinId + " effectuée le " +
                searchTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> findRendezVous(
            @RequestParam String medecineId,
            @RequestParam LocalDateTime dateTime) {

        List<RendezVousDTO> rendezVous = rendezVousService.findRendezVous(medecineId, dateTime);
        ApiResponse<List<RendezVousDTO>> response = ApiResponse.success(
                rendezVous,
                "Recherche de rendez-vous effectuée avec succès",
                HttpStatus.OK
        );

        LocalDateTime searchTime = response.getLocalDateTime();
        System.out.println("Recherche de rendez-vous pour le médecin " + medecineId +
                " à la date " + dateTime.format(formatter) +
                " effectuée le " + searchTime.format(formatter));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/annuler/{id}")
    public ResponseEntity<ApiResponse<Void>> annulerRendezVous(@PathVariable String id) {
        rendezVousService.annulerRendezVous(id);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Rendez-vous annulé avec succès",
                HttpStatus.NO_CONTENT
        );

        LocalDateTime cancellationTime = response.getLocalDateTime();
        System.out.println("Annulation du rendez-vous " + id + " effectuée le " +
                cancellationTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}