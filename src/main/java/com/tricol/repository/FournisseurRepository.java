package com.tricol.repository;

import com.tricol.model.Fournisseur;
import java.util.List;
import java.util.Optional;

public interface FournisseurRepository {
    Fournisseur save(Fournisseur fournisseur);
    Optional<Fournisseur> findById(Long id);
    List<Fournisseur> findAll();
    void deleteById(Long id);
    long count();
}
