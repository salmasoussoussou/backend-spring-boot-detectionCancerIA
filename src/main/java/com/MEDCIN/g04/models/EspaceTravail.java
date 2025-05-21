package com.MEDCIN.g04.models;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "espaces_travail")
public class EspaceTravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 255) // À adapter selon ta structure
    private String adresse; // Assure-tou que le nom correspond à la colonne en BDD

    @OneToMany(mappedBy = "espaceTravail", cascade = CascadeType.ALL)
    private List<Medecin> medecins;

    @OneToMany(mappedBy = "espaceTravail", cascade = CascadeType.ALL)
    private List<Patient> patient;



    // Constructeurs
    public EspaceTravail() {}

    public EspaceTravail(String nom, String specialite) {
        this.nom = nom;

    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }





    @Override
    public String toString() {
        return "EspaceTravail{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}