package com.MEDCIN.g04.services;

import com.MEDCIN.g04.dto.DiagnosticDTO;
import java.util.List;

public interface DiagnosticService {
    DiagnosticDTO saveDiagnostic(DiagnosticDTO dto);
    List<DiagnosticDTO> getAllDiagnostics();
    DiagnosticDTO getDiagnosticById(Long id);
    void deleteDiagnostic(Long id);
}
