package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.models.EspaceTravail;
import com.MEDCIN.g04.repositories.EspaceTravailRepository;
import com.MEDCIN.g04.repositories.PatientRepository;
import com.MEDCIN.g04.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }
    @Override
    public List<Patient> getLast5Patients() {
        return patientRepository.findTop5ByOrderByIdDesc();
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();

    }


    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setNom(patientDetails.getNom());
            patient.setAge(patientDetails.getAge());
            patient.setSexe(patientDetails.getSexe());
            if (patientDetails.getEspaceTravail() != null) {
                patient.setEspaceTravail(patientDetails.getEspaceTravail());
            }
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Médecin non trouvé avec l'id: " + id));

    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    @Override
    public long countPatients() {
        return patientRepository.count();
    }
}
