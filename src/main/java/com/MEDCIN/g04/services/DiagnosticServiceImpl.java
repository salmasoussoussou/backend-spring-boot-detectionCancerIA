package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.dto.DiagnosticDTO;
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
import java.util.stream.Collectors;

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
    public DiagnosticDTO saveDiagnostic(DiagnosticDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));
        Medecin medecin = medecinRepository.findById(dto.getMedecinId())
                .orElseThrow(() -> new RuntimeException("Médecin introuvable"));
        AnalyseAI analyse = analyseAIRepository.findById(dto.getAnalyseAIId())
                .orElseThrow(() -> new RuntimeException("AnalyseAI introuvable"));

        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setStatut(dto.getStatut());
        diagnostic.setCommentaire(dto.getCommentaire());
        diagnostic.setPartageAvecPatient(dto.isPartageAvecPatient());
        diagnostic.setPatient(patient);
        diagnostic.setMedecin(medecin);
        diagnostic.setAnalyseAI(analyse);

        Diagnostic saved = diagnosticRepository.save(diagnostic);
        return mapToDTO(saved);
    }

    @Override
    public List<DiagnosticDTO> getAllDiagnostics() {
        return diagnosticRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosticDTO getDiagnosticById(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnostic non trouvé"));
        return mapToDTO(diagnostic);
    }

    @Override
    public void deleteDiagnostic(Long id) {
        diagnosticRepository.deleteById(id);
    }

    // Méthode utilitaire pour conversion
    private DiagnosticDTO mapToDTO(Diagnostic diagnostic) {
        DiagnosticDTO dto = new DiagnosticDTO();
        dto.setId(diagnostic.getId());
        dto.setStatut(diagnostic.getStatut());
        dto.setCommentaire(diagnostic.getCommentaire());
        dto.setPartageAvecPatient(diagnostic.isPartageAvecPatient());
        dto.setPatientId(diagnostic.getPatientId());
        dto.setMedecinId(diagnostic.getMedecinId());
        dto.setAnalyseAIId(diagnostic.getAnalyseAIId());
        return dto;
    }
}
