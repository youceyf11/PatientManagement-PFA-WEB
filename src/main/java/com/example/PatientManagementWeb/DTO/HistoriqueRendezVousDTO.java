package com.example.PatientManagementWeb.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriqueRendezVousDTO {
    private String id;
    private LocalDateTime dateTime;
    private String motif;
    private String statut;
    private String patientId;
    private String medecinId;
    private String prescriptions;
    private LocalDateTime consultationDate;
}
