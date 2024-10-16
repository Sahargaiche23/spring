package com.example.foyerr.services;

import com.example.foyerr.Entites.Reservation;
import com.example.foyerr.Entites.Chambre; // Ensure you import Chambre
import com.example.foyerr.Entites.Etudiant; // Ensure you import Etudiant
import com.example.foyerr.Repositories.ReservationRepository;
import com.example.foyerr.Repositories.ChambreRepository; // Repository for Chambre
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ChambreRepository chambreRepository; // Inject Chambre repository

    @Autowired
    private com.example.foyerr.repositories.EtudiantRepository etudiantRepository; // Inject Etudiant repository

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        // Check if Chambre exists
        Optional<Chambre> optionalChambre = chambreRepository.findById(reservation.getChambre().getIdChambre());
        if (optionalChambre.isPresent()) {
            reservation.setChambre(optionalChambre.get());
        } else {
            throw new RuntimeException("Chambre not found");
        }

        // Check if Etudiants exist
        if (reservation.getEtudiants() != null) {
            for (Etudiant etudiant : reservation.getEtudiants()) {
                Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant());
                if (optionalEtudiant.isPresent()) {
                    etudiant = optionalEtudiant.get(); // Set the managed Etudiant instance
                } else {
                    throw new RuntimeException("Etudiant not found: " + etudiant.getIdEtudiant());
                }
            }
        }

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setAnneeUniversitaire(reservationDetails.getAnneeUniversitaire());
        reservation.setEstValide(reservationDetails.isEstValide());
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
    }
}
