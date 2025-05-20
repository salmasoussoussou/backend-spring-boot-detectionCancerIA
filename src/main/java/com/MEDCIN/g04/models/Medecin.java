package com.MEDCIN.g04.models;

import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name = "medecin")
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "espace_travail_id")
    private EspaceTravail espaceTravail;

    @OneToMany(mappedBy = "medecin")
    private Set<Patient> patients = new HashSet<>();

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageMedicale> imagesMedicales = new ArrayList<>();

    @OneToMany(mappedBy = "medecin")
    private List<Diagnostic> diagnostics;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin")
    private UserInfo userInfo;


    public Medecin() {}

    public Medecin(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Ajoutez ces méthodes
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EspaceTravail getEspaceTravail() {
        return espaceTravail;
    }

    public void setEspaceTravail(EspaceTravail espaceTravail) {
        this.espaceTravail = espaceTravail;
    }

    public Long getEspaceTravailId() {
        return (espaceTravail != null) ? espaceTravail.getId() : null;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", espaceTravailId=" + getEspaceTravailId() +
                '}';
    }
}