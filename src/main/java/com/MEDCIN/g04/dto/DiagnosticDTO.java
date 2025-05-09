package com.MEDCIN.g04.dto;

public class DiagnosticDTO {
    private Long id;
    private String statut;
    private String commentaire;
    private Long patientId;
    private Long medecinId;
    private Long analyseAIId;
    private boolean partageAvecPatient;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getMedecinId() { return medecinId; }
    public void setMedecinId(Long medecinId) { this.medecinId = medecinId; }

    public Long getAnalyseAIId() { return analyseAIId; }
    public void setAnalyseAIId(Long analyseAIId) { this.analyseAIId = analyseAIId; }

    public boolean isPartageAvecPatient() { return partageAvecPatient; }
    public void setPartageAvecPatient(boolean partageAvecPatient) {
        this.partageAvecPatient = partageAvecPatient;
    }
}
