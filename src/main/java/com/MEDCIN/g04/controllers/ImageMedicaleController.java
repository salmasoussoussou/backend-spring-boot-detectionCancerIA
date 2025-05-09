package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.ImageMedicaleDTO;
import com.MEDCIN.g04.models.ImageMedicale;
import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.repositories.PatientRepository;
import com.MEDCIN.g04.repositories.MedecinRepository;
import com.MEDCIN.g04.services.ImageMedicaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImageMedicaleController {

    private final ImageMedicaleService imageService;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    @Autowired
    public ImageMedicaleController(ImageMedicaleService imageService,
                                   PatientRepository patientRepository,
                                   MedecinRepository medecinRepository) {
        this.imageService = imageService;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }

    private ImageMedicaleDTO convertToDTO(ImageMedicale image) {
        return new ImageMedicaleDTO(
                image.getId(),
                image.getType(),
                image.getChemin(),
                image.getDescription(),
                image.getPatient() != null ? image.getPatient().getId() : null,
                image.getMedecin() != null ? image.getMedecin().getId() : null
        );
    }

    // 🔹 Ajouter une image médicale
    @PostMapping
    public ResponseEntity<ImageMedicaleDTO> createImage(@RequestBody ImageMedicale image) {
        if (image.getPatient() == null || image.getPatient().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID patient est requis");
        }
        if (image.getMedecin() == null || image.getMedecin().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID médecin est requis");
        }

        Patient patient = patientRepository.findById(image.getPatient().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient non trouvé"));

        Medecin medecin = medecinRepository.findById(image.getMedecin().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médecin non trouvé"));

        image.setPatient(patient);
        image.setMedecin(medecin);

        ImageMedicale savedImage = imageService.saveImage(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedImage));
    }

    // 🔹 Afficher toutes les images
    @GetMapping
    public ResponseEntity<List<ImageMedicaleDTO>> getAllImages() {
        List<ImageMedicaleDTO> images = imageService.getAllImages().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(images);
    }

    // 🔹 Afficher une image par ID
    @GetMapping("/{id}")
    public ResponseEntity<ImageMedicaleDTO> getImageById(@PathVariable Long id) {
        ImageMedicale image = imageService.getImageById(id);
        if (image == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image non trouvée");
        }
        return ResponseEntity.ok(convertToDTO(image));
    }

    // 🔹 Modifier une image
    @PutMapping("/{id}")
    public ResponseEntity<ImageMedicaleDTO> updateImage(@PathVariable Long id, @RequestBody ImageMedicale imageDetails) {
        ImageMedicale updatedImage = imageService.updateImage(id, imageDetails);
        return ResponseEntity.ok(convertToDTO(updatedImage));
    }

    // 🔹 Supprimer une image
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Afficher les images d'un patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ImageMedicaleDTO>> getImagesByPatient(@PathVariable Long patientId) {
        List<ImageMedicaleDTO> images = imageService.getImagesByPatientId(patientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(images);
    }

    // 🔹 Afficher les images d'un médecin
    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<ImageMedicaleDTO>> getImagesByMedecin(@PathVariable Long medecinId) {
        List<ImageMedicaleDTO> images = imageService.getImagesByMedecinId(medecinId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(images);
    }
}