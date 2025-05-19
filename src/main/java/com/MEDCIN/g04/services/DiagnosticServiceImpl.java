package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.AnalyseAI;
import com.MEDCIN.g04.models.Diagnostic;
import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.repositories.AnalyseAIRepository;
import com.MEDCIN.g04.repositories.DiagnosticRepository;
import com.MEDCIN.g04.repositories.MedecinRepository;
import com.MEDCIN.g04.repositories.PatientRepository;
import com.MEDCIN.g04.services.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private AnalyseAIRepository analyseAIRepository;

    @Override
    public Diagnostic saveDiagnostic(Diagnostic diagnostic) {
        // Vérification si les entités liées existent
        Patient patient = patientRepository.findById(diagnostic.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));

        Medecin medecin = medecinRepository.findById(diagnostic.getMedecin().getId())
                .orElseThrow(() -> new RuntimeException("Médecin introuvable"));

        AnalyseAI analyse = analyseAIRepository.findById(diagnostic.getAnalyseAI().getId())
                .orElseThrow(() -> new RuntimeException("Analyse AI introuvable"));

        diagnostic.setPatient(patient);
        diagnostic.setMedecin(medecin);
        diagnostic.setAnalyseAI(analyse);

        return diagnosticRepository.save(diagnostic);
    }

    @Override
    public List<Diagnostic> getAllDiagnostics() {
        return diagnosticRepository.findAll();
    }

    @Override
    public Optional<Diagnostic> getDiagnosticById(Long id) {
        return diagnosticRepository.findById(id);
    }

    @Override
    public Diagnostic updateDiagnostic(Long id, Diagnostic diagnosticDetails) {
        return diagnosticRepository.findById(id).map(diagnostic -> {
            diagnostic.setStatut(diagnosticDetails.getStatut());
            diagnostic.setCommentaire(diagnosticDetails.getCommentaire());
            if (diagnosticDetails.getPatient() != null) {
                diagnostic.setPatient(diagnosticDetails.getPatient());
            }
            if (diagnosticDetails.getMedecin() != null) {
                diagnostic.setMedecin(diagnosticDetails.getMedecin());
            }
            if (diagnosticDetails.getAnalyseAI() != null) {
                diagnostic.setAnalyseAI(diagnosticDetails.getAnalyseAI());
            }
            return diagnosticRepository.save(diagnostic);
        }).orElseThrow(() -> new RuntimeException("Diagnostic non trouvé avec l'id: " + id));
    }

    @Override
    public void deleteDiagnostic(Long id) {
        diagnosticRepository.deleteById(id);
    }

    @Override
    public long countDiagnostic() {
        return diagnosticRepository.count();
    }
}
