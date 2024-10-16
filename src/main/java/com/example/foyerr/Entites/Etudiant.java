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
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;

    private String nomEt;
    private String prenomEt;
    private Long cin;
    private String ecole;

    @JsonFormat(pattern = "yyyy-MM-dd")  // Ensures correct date format in JSON
    private Date dateNaissance;

    // Inverse side of the many-to-many relationship with Reservation
    @ManyToMany(mappedBy = "etudiants")
    private List<Reservation> reservations;
}
