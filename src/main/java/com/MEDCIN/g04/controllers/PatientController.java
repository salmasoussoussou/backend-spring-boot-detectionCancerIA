package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.PatientDTO;
import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    private PatientDTO convertToDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getNom(),
                patient.getPrenom(),
                patient.getAge(),
                patient.getEmail(),
                patient.getSexe(),
                patient.getMedecin() != null ? patient.getMedecin().getId() : null,
                patient.getEspaceTravail() != null ? patient.getEspaceTravail().getId() : null
        );
    }


    // 🔹 Ajouter un patient
    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody Patient patient) {
        Patient saved = patientService.savePatient(patient);
        return ResponseEntity.status(201).body(convertToDTO(saved));  // Code 201 pour la création
    }

    // 🔹 Afficher tous les patients
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patients);  // Code 200 pour la récupération des données
    }

    // 🔹 Afficher un patient par ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
        return ResponseEntity.ok(convertToDTO(patient));  // Code 200 pour la récupération d'un patient
    }

    // 🔹 Modifier un patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updated = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(convertToDTO(updated));  // Code 200 pour la mise à jour
    }

    // 🔹 Supprimer un patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();  // Code 204 pour la suppression
    }
}
