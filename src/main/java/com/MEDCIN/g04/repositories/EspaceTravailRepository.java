package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.EspaceTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspaceTravailRepository extends JpaRepository<EspaceTravail, Long> {

//    // Trouver par nom exact (case sensitive)
//    Optional<EspaceTravail> findByNom(String nom);
//
//    // Trouver par nom contenant (ignore case)
//    List<EspaceTravail> findByNomContainingIgnoreCase(String nomPartiel);
//
//    // Trouver par spécialité
//    List<EspaceTravail> findBySpecialite(String specialite);
//
//    // Requête JPQL personnalisée pour trouver les espaces avec un médecin spécifique
//    @Query("SELECT e FROM EspaceTravail e JOIN e.medecins m WHERE m.id = :medecinId")
//    List<EspaceTravail> findByMedecinId(@Param("medecinId") Long medecinId);
//
//    // Requête native pour compter le nombre de patients par espace
//    @Query(value = "SELECT COUNT(*) FROM patients WHERE espace_travail_id = :espaceId", nativeQuery = true)
//    int countPatientsByEspaceId(@Param("espaceId") Long espaceId);
//
//    // Vérifier si un nom d'espace existe déjà (pour la création)
//    boolean existsByNom(String nom);
}