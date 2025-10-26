package com.tricol.repository;

import com.tricol.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findBySocieteContaining(String societe);
    List<Fournisseur> findByEmailEndingWith(String domain);
}
