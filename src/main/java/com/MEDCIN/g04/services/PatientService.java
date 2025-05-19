package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.Patient;
import java.util.List;
import java.util.Optional; // permet de retourner un objet qui peut être nul (utile pour éviter les erreurs de type null).

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    List<Patient> getLast5Patients();
    Optional<Patient> getPatientById(Long id);
    Patient updatePatient(Long id, Patient patientDetails);
    void deletePatient(Long id);
    long countPatients();
}