package com.MEDCIN.g04.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import com.MEDCIN.g04.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findTop5ByOrderByIdDesc();

}
