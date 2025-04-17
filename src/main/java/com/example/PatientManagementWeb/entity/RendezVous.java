package com.example.PatientManagementWeb.entity;

import com.example.PatientManagementWeb.Enum.Statut;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVous {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;

   private LocalDateTime dateHeure;
   private String motif;
   private Statut statut;

   @ManyToOne
   @JoinColumn(name = "patient_id")
   private Patient patient;

   @ManyToOne
   @JoinColumn(name = "medecin_id")
   private Medecin medecin;


}
