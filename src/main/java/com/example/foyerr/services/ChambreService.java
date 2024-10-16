package com.example.foyerr.services;


import com.example.foyerr.Entites.Chambre;
import com.example.foyerr.Repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    public Chambre updateChambre(Long id, Chambre chambreDetails) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));
        chambre.setNumeroChambre(chambreDetails.getNumeroChambre());
        chambre.setTypeC(chambreDetails.getTypeC());
        return chambreRepository.save(chambre);
    }

    public void deleteChambre(Long id) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));
        chambreRepository.delete(chambre);
    }
}
