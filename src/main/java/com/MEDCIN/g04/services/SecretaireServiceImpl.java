package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.Secretaire;
import com.MEDCIN.g04.repositories.SecretaireRepository;
import com.MEDCIN.g04.services.SecretaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecretaireServiceImpl implements SecretaireService {

    private final SecretaireRepository secretaireRepository;

    @Autowired
    public SecretaireServiceImpl(SecretaireRepository secretaireRepository) {
        this.secretaireRepository = secretaireRepository;
    }

    @Override
    public List<Secretaire> getAllSecretaires() {
        return secretaireRepository.findAll();
    }

    @Override
    public Optional<Secretaire> getSecretaireById(Long id) {
        return secretaireRepository.findById(id);
    }

    @Override
    public Secretaire saveSecretaire(Secretaire secretaire) {
        return secretaireRepository.save(secretaire);
    }

    @Override
    public Secretaire updateSecretaire(Long id, Secretaire secretaire) {
        return secretaireRepository.findById(id).map(existing -> {
            existing.setNom(secretaire.getNom());
            existing.setPrenom(secretaire.getPrenom());
            existing.setEmail(secretaire.getEmail());
            existing.setEspaceTravail(secretaire.getEspaceTravail());
            return secretaireRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Secrétaire non trouvée avec l'id : " + id));
    }

    @Override
    public void deleteSecretaire(Long id) {
        if (secretaireRepository.existsById(id)) {
            secretaireRepository.deleteById(id);
        } else {
            throw new RuntimeException("Secrétaire non trouvée avec l'id : " + id);
        }
    }
    @Override
    public long countSecretaires() {
        return secretaireRepository.count();
    }
}
