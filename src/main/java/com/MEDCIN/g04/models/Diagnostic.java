package com.MEDCIN.g04.models;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnostics")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String statut; // NORMAL, SUSPECT, CANCER

    @Column(length = 1000)
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id", nullable = false)
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "analyse_ai_id", nullable = false)
    private AnalyseAI analyseAI;


    @Column(name = "partage_avec_patient", nullable = false)
    private boolean partageAvecPatient = false;

    public boolean isPartageAvecPatient() {
        return partageAvecPatient;
    }

    public void setPartageAvecPatient(boolean partageAvecPatient) {
        this.partageAvecPatient = partageAvecPatient;
    }

    // Constructeurs
    public Diagnostic() {
    }

    public Diagnostic(String statut, String commentaire, Patient patient, Medecin medecin, AnalyseAI analyseAI) {
        this.statut = statut;
        this.commentaire = commentaire;
        this.patient = patient;
        this.medecin = medecin;
        this.analyseAI = analyseAI;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }
    // ✅ SETTER pour l'id
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public AnalyseAI getAnalyseAI() {
        return analyseAI;
    }

    public void setAnalyseAI(AnalyseAI analyseAI) {
        this.analyseAI = analyseAI;
    }

    // 🔍 Méthodes pour afficher les IDs associés
    public Long getPatientId() {
        return (patient != null) ? patient.getId() : null;
    }

    public Long getMedecinId() {
        return (medecin != null) ? medecin.getId() : null;
    }

    public Long getAnalyseAIId() {
        return (analyseAI != null) ? analyseAI.getId() : null;
    }

    @Override
    public String toString() {
        return "Diagnostic{" +
                "id=" + id +
                ", statut='" + statut + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", patientId=" + getPatientId() +
                ", medecinId=" + getMedecinId() +
                ", analyseAIId=" + getAnalyseAIId() +
                '}';
    }
}
