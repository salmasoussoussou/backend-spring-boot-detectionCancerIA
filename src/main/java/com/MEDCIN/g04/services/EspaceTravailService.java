package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.EspaceTravail;
import java.util.List;
import java.util.Optional;

public interface EspaceTravailService {
    List<EspaceTravail> getAllEspaces();
    Optional<EspaceTravail> getEspaceById(Long id); // Gardez seulement cette méthode
    EspaceTravail createEspace(EspaceTravail espaceTravail);
    EspaceTravail updateEspace(Long id, EspaceTravail updatedEspace);
    void deleteEspace(Long id);
}

