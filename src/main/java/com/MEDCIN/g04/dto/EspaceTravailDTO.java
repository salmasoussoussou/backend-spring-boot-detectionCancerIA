package com.MEDCIN.g04.dto;

import java.util.List;

public class EspaceTravailDTO {
    private Long id;
    private String nom;
    private String adresse;
//    private List<Long> medecins;
//    private List<Long> patients;
    private List<Long> secretaires;

    // Constructeur vide
    public EspaceTravailDTO() {}

    // Nouveau constructeur simplifié
    public EspaceTravailDTO(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Constructeur complet
    public EspaceTravailDTO(Long id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
//        this.medecins = medecins;
//        this.patients = patients;
//        this.secretaires = secretaires;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

//    public List<Long> getMedecins() { return medecins; }
//    public void setMedecins(List<Long> medecins) { this.medecins = medecins; }
//
//    public List<Long> getPatients() { return patients; }
//    public void setPatients(List<Long> patients) { this.patients = patients; }
//
//    public List<Long> getSecretaires() { return secretaires; }
//    public void setSecretaires(List<Long> secretaires) { this.secretaires = secretaires; }
}