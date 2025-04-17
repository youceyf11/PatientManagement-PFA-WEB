package com.example.PatientManagementWeb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class HistoriqueRendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime dateTime;
    private String motif;
    private String statut;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private RendezVous rendezVous;

    private String medecinId;


    @CreationTimestamp
    private LocalDateTime creationDate;

}
