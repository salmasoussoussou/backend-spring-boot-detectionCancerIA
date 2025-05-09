package com.MEDCIN.g04.dto;

public class SecretaireDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private EspaceTravailDTO espaceTravail;

    // Constructeurs
    public SecretaireDTO() {}

    public SecretaireDTO(Long id, String nom, String prenom,
                         String email, EspaceTravailDTO espaceTravail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.espaceTravail = espaceTravail;
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

    public EspaceTravailDTO getEspaceTravail() { return espaceTravail; }
    public void setEspaceTravail(EspaceTravailDTO espaceTravail) {
        this.espaceTravail = espaceTravail;
    }
}