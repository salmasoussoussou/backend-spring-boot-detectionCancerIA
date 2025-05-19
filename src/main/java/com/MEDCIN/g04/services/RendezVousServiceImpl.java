package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.RendezVous;
import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.models.Secretaire;
import com.MEDCIN.g04.repositories.PatientRepository;
import com.MEDCIN.g04.repositories.SecretaireRepository;
import com.MEDCIN.g04.repositories.RendezVousRepository;
import com.MEDCIN.g04.services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rendezVousRepo;
    private final PatientRepository patientRepo;
    private final SecretaireRepository secretaireRepo;

    @Autowired
    public RendezVousServiceImpl(RendezVousRepository rendezVousRepo, PatientRepository patientRepo, SecretaireRepository secretaireRepo) {
        this.rendezVousRepo = rendezVousRepo;
        this.patientRepo = patientRepo;
        this.secretaireRepo = secretaireRepo;
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rdv) {
        // Charger les objets complets à partir des IDs
        Long patientId = rdv.getPatient().getId();
        Long secretaireId = rdv.getSecretaire().getId();

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient introuvable ID : " + patientId));
        Secretaire secretaire = secretaireRepo.findById(secretaireId)
                .orElseThrow(() -> new RuntimeException("Secrétaire introuvable ID : " + secretaireId));

        rdv.setPatient(patient);
        rdv.setSecretaire(secretaire);

        return rendezVousRepo.save(rdv);
    }

    @Override
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepo.findAll();
    }

    @Override
    public RendezVous getRendezVousById(Long id) {
        return rendezVousRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteRendezVous(Long id) {
        rendezVousRepo.deleteById(id);
    }
    @Override
    public long countRendezVous() {
        return rendezVousRepo.count();
    }
}
