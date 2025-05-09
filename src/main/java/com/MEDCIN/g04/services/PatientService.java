package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Patient updatePatient(Long id, Patient patientDetails);
    void deletePatient(Long id);
}