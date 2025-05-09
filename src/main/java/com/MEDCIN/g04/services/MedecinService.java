package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.Medecin;

import java.util.List;
import java.util.Optional;

public interface MedecinService {
    Medecin saveMedecin(Medecin medecin);
    List<Medecin> getAllMedecins();
    Optional<Medecin> getMedecinById(Long id);
    Medecin updateMedecin(Long id, Medecin medecinDetails);
    void deleteMedecin(Long id);
    Optional<Medecin> findByEmail(String email);
}
