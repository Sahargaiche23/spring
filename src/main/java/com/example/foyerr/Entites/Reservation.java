package com.example.foyerr.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @JsonFormat(pattern = "yyyy-MM-dd")  // Ensures correct date format in JSON
    private Date anneeUniversitaire;

    private boolean estValide;

    // Many-to-many relationship with Etudiant
    @ManyToMany
    @JoinTable(
            name = "reservation_etudiant",  // Join table name
            joinColumns = @JoinColumn(name = "reservation_id"),  // Join column for Reservation
            inverseJoinColumns = @JoinColumn(name = "etudiant_id")  // Join column for Etudiant
    )
    private List<Etudiant> etudiants;

    // Many-to-one relationship with Chambre
    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
}
