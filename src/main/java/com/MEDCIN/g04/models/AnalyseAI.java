package com.MEDCIN.g04.models;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "analyses_ai")
public class AnalyseAI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double score;

    @Column(length = 2000)
    private String zonesAnormales; // JSON ou texte décrivant les zones

    @OneToOne
    @JoinColumn(name = "image_medicale_id", nullable = false)
    private ImageMedicale image; // Relation vers l’image analysée
    @OneToMany(mappedBy = "analyseAI", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Diagnostic> diagnostics;


    // Constructeurs
    public AnalyseAI() {
    }

    public AnalyseAI(Double score, String zonesAnormales, ImageMedicale image) {
        this.score = score;
        this.zonesAnormales = zonesAnormales;
        this.image = image;
    }

    // Getters et Setters
    public Long getId() {
        return id;
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

    public ImageMedicale getImage() {
        return image;
    }

    public void setImage(ImageMedicale image) {
        this.image = image;
    }

    // 🔍 Méthode pour afficher l'ID de l’image liée
    public Long getImageId() {
        return (image != null) ? image.getId() : null;
    }

    @Override
    public String toString() {
        return "analyseai{" +
                "id=" + id +
                ", score=" + score +
                ", zonesAnormales='" + zonesAnormales + '\'' +
                ", imageId=" + getImageId() +
                '}';
    }
}
