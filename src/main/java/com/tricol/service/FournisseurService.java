package com.tricol.service;

import com.tricol.model.Fournisseur;
import java.util.List;
import java.util.Optional;

public interface FournisseurService {
    Fournisseur save(Fournisseur fournisseur);
    Optional<Fournisseur> findById(Long id);
    List<Fournisseur> findAll();
    void deleteById(Long id);
    Fournisseur update(Long id, Fournisseur fournisseur);
}
