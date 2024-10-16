package com.example.foyerr.Controllers;

import com.example.foyerr.Entites.Chambre;
import com.example.foyerr.Repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambres")
public class ChambreController {

    @Autowired
    private ChambreRepository chambreRepository;

    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @PostMapping
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));
        return ResponseEntity.ok(chambre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id, @RequestBody Chambre chambreDetails) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));

        chambre.setNumeroChambre(chambreDetails.getNumeroChambre());
        chambre.setTypeC(chambreDetails.getTypeC());

        final Chambre updatedChambre = chambreRepository.save(chambre);
        return ResponseEntity.ok(updatedChambre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));
        chambreRepository.delete(chambre);
        return ResponseEntity.noContent().build();
    }
}
