package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.Diagnostic;
import com.MEDCIN.g04.models.Patient;

import java.util.List;
import java.util.Optional;

public interface DiagnosticService {
    Diagnostic saveDiagnostic(Diagnostic diagnostic);
    List<Diagnostic> getAllDiagnostics();
    Optional<Diagnostic> getDiagnosticById(Long id);
    Diagnostic updateDiagnostic(Long id, Diagnostic diagnosticDetails);
    void deleteDiagnostic(Long id);
    long countDiagnostic();

}

