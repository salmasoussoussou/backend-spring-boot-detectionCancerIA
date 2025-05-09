package com.MEDCIN.g04.models;

import jakarta.persistence.*;

@Entity
@Table(name = "images_medicales")
public class ImageMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 255)
    private String chemin;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id", nullable = false)
    private Medecin medecin;

    @Column(length = 500)
    private String description;

    // Constructeurs
    public ImageMedicale() {
    }

    public ImageMedicale(String type, String chemin, Patient patient, Medecin medecin, String description) {
        this.type = type;
        this.chemin = chemin;
        this.patient = patient;
        this.medecin = medecin;
        this.description = description;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImageMedicale{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", chemin='" + chemin + '\'' +
                ", patient=" + (patient != null ? patient.getId() : null) +
                ", medecin=" + (medecin != null ? medecin.getId() : null) +
                ", description='" + description + '\'' +
                '}';
    }
}
