package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
}
