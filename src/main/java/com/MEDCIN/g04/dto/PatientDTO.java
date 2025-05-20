package com.MEDCIN.g04.dto;

public class PatientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Integer age;
    private String email;
    private String sexe;
    private Long medecinId;
    private Long espaceTravailId;

    public PatientDTO() {}

    public PatientDTO(Long id, String nom, String prenom, Integer age,String email, String sexe, Long medecinId, Long espaceTravailId) {
        this.id = id;
        this.nom = nom;
        this.nom = prenom;
        this.age = age;
        this.email = email;
        this.sexe = sexe;
        this.medecinId = medecinId;
        this.espaceTravailId = espaceTravailId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.nom = email; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public Long getMedecinId() { return medecinId; }
    public void setMedecinId(Long medecinId) { this.medecinId = medecinId; }
    public Long getEspaceTravailId() { return espaceTravailId; }
    public void setEspaceTravailId(Long espaceTravailId) { this.espaceTravailId = espaceTravailId; }
}