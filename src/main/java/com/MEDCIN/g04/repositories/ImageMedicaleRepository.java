package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.ImageMedicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMedicaleRepository extends JpaRepository<ImageMedicale, Long> {
    List<ImageMedicale> findByPatient_Id(Long patientId);
    List<ImageMedicale> findByMedecin_Id(Long medecinId);
}
