package com.tricol.repository.impl;

import com.tricol.model.Fournisseur;
import com.tricol.repository.FournisseurRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class FournisseurRepositoryImpl implements FournisseurRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Fournisseur save(Fournisseur fournisseur) {
        if (fournisseur.getId() == null) {
            entityManager.persist(fournisseur);
            return fournisseur;
        } else {
            return entityManager.merge(fournisseur);
        }
    }

    @Override
    public Optional<Fournisseur> findById(Long id) {
        Fournisseur fournisseur = entityManager.find(Fournisseur.class, id);
        return Optional.ofNullable(fournisseur);
    }

    @Override
    public List<Fournisseur> findAll() {
        return entityManager.createQuery("SELECT f FROM Fournisseur f", Fournisseur.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Fournisseur fournisseur = entityManager.find(Fournisseur.class, id);
        if (fournisseur != null) {
            entityManager.remove(fournisseur);
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(f) FROM Fournisseur f", Long.class).getSingleResult();
    }
}
