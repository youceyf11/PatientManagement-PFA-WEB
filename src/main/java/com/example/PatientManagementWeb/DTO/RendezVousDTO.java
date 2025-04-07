package com.example.PatientManagementWeb.DTO;

import com.example.PatientManagementWeb.Enum.Statut;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RendezVousDTO {
    private UUID id;

    @NotNull(message = "la date et l'heure sont obligatoires")
    @Future(message = "la date doit etre dans le futur")
    private LocalDateTime dateHeure;

    @NotBlank(message = "le motif est obligatoire")
    private String motif;

    @NotNull(message = "le statut est obligatoire")
    private Statut statut;

    @NotBlank(message = "l'identifiant du patient est obligatoire")
    private String patientId;

}
