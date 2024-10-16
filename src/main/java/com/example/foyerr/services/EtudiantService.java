package com.example.foyerr.services;

import com.example.foyerr.Entites.Etudiant;
import com.example.foyerr.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Etudiant not found"));
        etudiant.setNomEt(etudiantDetails.getNomEt());
        etudiant.setPrenomEt(etudiantDetails.getPrenomEt());
        etudiant.setCin(etudiantDetails.getCin());
        etudiant.setEcole(etudiantDetails.getEcole());
        etudiant.setDateNaissance(etudiantDetails.getDateNaissance());
        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Etudiant not found"));
        etudiantRepository.delete(etudiant);
    }
}
