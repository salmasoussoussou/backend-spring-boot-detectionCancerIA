package com.MEDCIN.g04.dto;

import com.MEDCIN.g04.models.ImageMedicale;
import com.MEDCIN.g04.models.AnalyseAI;

public class AnalyseAIDTO {

    private Long id;
    private Double score;
    private String zonesAnormales;
    private ImageMedicaleDTO image;

    public AnalyseAIDTO(AnalyseAI analyse) {
        this.id = analyse.getId();
        this.score = analyse.getScore();
        this.zonesAnormales = analyse.getZonesAnormales();

        // Mapper l'image en DTO
        ImageMedicale img = analyse.getImage();
        this.image = new ImageMedicaleDTO(
                img.getId(),
                img.getType(),
                img.getChemin(),
                img.getDescription(),
                (img.getPatient() != null) ? img.getPatient().getId() : null,
                (img.getMedecin() != null) ? img.getMedecin().getId() : null
        );
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getZonesAnormales() {
        return zonesAnormales;
    }

    public void setZonesAnormales(String zonesAnormales) {
        this.zonesAnormales = zonesAnormales;
    }

    public ImageMedicaleDTO getImage() {
        return image;
    }

    public void setImage(ImageMedicaleDTO image) {
        this.image = image;
    }
}
