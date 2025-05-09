package com.MEDCIN.g04.dto;

public class MedecinDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Long espaceTravailId;

    // Constructeurs
    public MedecinDTO() {}

    public MedecinDTO(Long id, String nom, String prenom, String email, Long espaceTravailId) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.espaceTravailId = espaceTravailId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getEspaceTravailId() { return espaceTravailId; }
    public void setEspaceTravailId(Long espaceTravailId) { this.espaceTravailId = espaceTravailId; }
}