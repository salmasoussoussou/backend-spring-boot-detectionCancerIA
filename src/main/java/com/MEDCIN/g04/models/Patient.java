package com.MEDCIN.g04.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientid")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;
    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false)
    private Integer age;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 10)
    private String sexe;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "espace_travail_id")
    private EspaceTravail espaceTravail;



    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageMedicale> images = new ArrayList<>();


@OneToMany(mappedBy = "patient")
private List<Diagnostic> diagnostics;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<RendezVous> rendezVousList;


    // Constructeur par défaut
    public Patient() {}

    // Constructeur avec tous les champs
    public Patient(String nom,String prenom, Integer age, String email, String sexe, Medecin medecin, EspaceTravail espaceTravail) {

        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.sexe = sexe;
        this.medecin = medecin;
        this.espaceTravail = espaceTravail;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }
    public EspaceTravail getEspaceTravail() { return espaceTravail; }
    public void setEspaceTravail(EspaceTravail espaceTravail) { this.espaceTravail = espaceTravail; }

    // Méthodes d'aide pour l'affichage dans toString
    public Long getMedecinId() {
        return medecin != null ? medecin.getId() : null;
    }

    public Long getEspaceTravailId() {
        return espaceTravail != null ? espaceTravail.getId() : null;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", sexe='" + sexe + '\'' +
                ", medecin=" + medecin +
                ", espaceTravail=" + espaceTravail +
                '}';
    }
}
