package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.EspaceTravail;
import com.MEDCIN.g04.repositories.EspaceTravailRepository;
import com.MEDCIN.g04.services.EspaceTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspaceTravailServiceImpl implements EspaceTravailService {

    private final EspaceTravailRepository espaceTravailRepository;

    @Autowired
    public EspaceTravailServiceImpl(EspaceTravailRepository espaceTravailRepository) {
        this.espaceTravailRepository = espaceTravailRepository;
    }

    @Override
    public List<EspaceTravail> getAllEspaces() {
        return espaceTravailRepository.findAll();
    }

    @Override
    public Optional<EspaceTravail> getEspaceById(Long id) {
        return espaceTravailRepository.findById(id);
    }

    @Override
    public EspaceTravail createEspace(EspaceTravail espaceTravail) {
        return espaceTravailRepository.save(espaceTravail);
    }

    @Override
    public EspaceTravail updateEspace(Long id, EspaceTravail updatedEspace) {
        return espaceTravailRepository.findById(id).map(espace -> {
            if (updatedEspace.getNom() != null) espace.setNom(updatedEspace.getNom());
            if (updatedEspace.getAdresse() != null) espace.setAdresse(updatedEspace.getAdresse());

            return espaceTravailRepository.save(espace);
        }).orElse(null);
    }

    @Override
    public void deleteEspace(Long id) {
        espaceTravailRepository.deleteById(id);
    }
}
