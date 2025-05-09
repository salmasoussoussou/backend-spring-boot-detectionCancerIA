package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.ImageMedicale;
import com.MEDCIN.g04.repositories.ImageMedicaleRepository;
import com.MEDCIN.g04.services.ImageMedicaleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.repositories.PatientRepository;
import com.MEDCIN.g04.repositories.MedecinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageMedicaleServiceImpl implements ImageMedicaleService {

    private final ImageMedicaleRepository imageRepo;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    @Autowired
    public ImageMedicaleServiceImpl(ImageMedicaleRepository imageRepo,
                                    PatientRepository patientRepository,
                                    MedecinRepository medecinRepository) {
        this.imageRepo = imageRepo;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }

    @Override
    public ImageMedicale saveImage(ImageMedicale image) {
        return imageRepo.save(image);
    }

    @Override
    public List<ImageMedicale> getAllImages() {
        return imageRepo.findAll();
    }

    @Override
    public ImageMedicale getImageById(Long id) {
        return imageRepo.findById(id).orElse(null);
    }
    @Override
    public ImageMedicale updateImage(Long id, ImageMedicale imageDetails) {
        return imageRepo.findById(id)
                .map(existingImage -> {
                    // Mise à jour des champs modifiables
                    if (imageDetails.getType() != null) {
                        existingImage.setType(imageDetails.getType());
                    }
                    if (imageDetails.getChemin() != null) {
                        existingImage.setChemin(imageDetails.getChemin());
                    }
                    if (imageDetails.getDescription() != null) {
                        existingImage.setDescription(imageDetails.getDescription());
                    }

                    // Mise à jour des relations si fournies
                    if (imageDetails.getPatient() != null && imageDetails.getPatient().getId() != null) {
                        Patient patient = patientRepository.findById(imageDetails.getPatient().getId())
                                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
                        existingImage.setPatient(patient);
                    }

                    if (imageDetails.getMedecin() != null && imageDetails.getMedecin().getId() != null) {
                        Medecin medecin = medecinRepository.findById(imageDetails.getMedecin().getId())
                                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
                        existingImage.setMedecin(medecin);
                    }

                    return imageRepo.save(existingImage);
                })
                .orElseThrow(() -> new RuntimeException("Image médicale non trouvée avec l'ID : " + id));
    }
    @Override
    public void deleteImage(Long id) {
        imageRepo.deleteById(id);
    }

    @Override
    public List<ImageMedicale> getImagesByPatientId(Long patientId) {
        return imageRepo.findByPatient_Id(patientId);
    }

    @Override
    public List<ImageMedicale> getImagesByMedecinId(Long medecinId) {
        return imageRepo.findByMedecin_Id(medecinId);
    }
}
