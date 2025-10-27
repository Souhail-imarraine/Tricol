package com.tricol.service.impl;

import com.tricol.model.Fournisseur;
import com.tricol.repository.FournisseurRepository;
import com.tricol.service.FournisseurService;
import java.util.List;
import java.util.Optional;

public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository repository;

    public void setRepository(FournisseurRepository repository) {
        this.repository = repository;
    }

    @Override
    public Fournisseur save(Fournisseur fournisseur) {
        return repository.save(fournisseur);
    }

    @Override
    public Optional<Fournisseur> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Fournisseur> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Fournisseur update(Long id, Fournisseur fournisseur) {
        fournisseur.setId(id);
        return repository.save(fournisseur);
    }
}
