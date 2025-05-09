package com.MEDCIN.g04.dto;

public class ImageMedicaleDTO {
    private Long id;
    private String type;
    private String chemin;
    private String description;
    private Long patientId;  // Juste l'ID
    private Long medecinId;  // Juste l'ID
    // + getters/setters


    public ImageMedicaleDTO() {
    }

    public ImageMedicaleDTO(Long id, String type, String chemin, String description, Long patientId, Long medecinId) {
        this.id = id;
        this.type = type;
        this.chemin = chemin;
        this.description = description;
        this.patientId = patientId;
        this.medecinId = medecinId;
    }
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

}


