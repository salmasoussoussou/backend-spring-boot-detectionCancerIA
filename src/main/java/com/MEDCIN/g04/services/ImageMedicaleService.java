package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.ImageMedicale;
import java.util.List;
import java.util.Optional;

public interface ImageMedicaleService {
    ImageMedicale saveImage(ImageMedicale image);
    List<ImageMedicale> getAllImages();
    ImageMedicale getImageById(Long id);
    ImageMedicale updateImage(Long id, ImageMedicale imageDetails); // Ajoutez cette ligne
    void deleteImage(Long id);
    List<ImageMedicale> getImagesByPatientId(Long patientId);
    List<ImageMedicale> getImagesByMedecinId(Long medecinId);
}