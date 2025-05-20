package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.Secretaire;

import java.util.List;
import java.util.Optional;

public interface SecretaireService {
    List<Secretaire> getAllSecretaires();
    Optional<Secretaire> getSecretaireById(Long id);
    Secretaire saveSecretaire(Secretaire secretaire);
    Secretaire updateSecretaire(Long id, Secretaire secretaire);
    void deleteSecretaire(Long id);
    // Ajoutez cette méthode
    long countSecretaires();}
