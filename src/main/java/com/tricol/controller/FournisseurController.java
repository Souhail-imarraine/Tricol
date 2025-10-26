package com.tricol.controller;
import com.tricol.model.Fournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tricol.service.FournisseurService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.findAll();
    }

    @GetMapping("/{id}")
    public Fournisseur getById(@PathVariable Long id){
        return fournisseurService.findById(id).orElse(null);
    }

    //@PostMapping = Répond aux requêtes POST (création)
    //@RequestBody = Récupère le JSON du body de la requête
    @PostMapping
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.save(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        return fournisseurService.update(id, fournisseur);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable long id){
        fournisseurService.deleteById(id);
    }

}
