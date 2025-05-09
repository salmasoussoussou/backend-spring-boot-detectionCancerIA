package com.MEDCIN.g04.models;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "espaces_travail")
@JsonInclude(JsonInclude.Include.ALWAYS) // Inclut même les champs null
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

//    // Relation OneToMany
//    @OneToMany(mappedBy = "espaceTravail") // Doit matcher le nom dans Medecin
//    private Set<Medecin> medecins = new HashSet<>();
//
//
//
//    // Relation OneToMany avec Patient
//    @OneToMany(mappedBy = "espaceTravail", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Patient> patients = new HashSet<>();
//
//    // Ajouter cette relation
//    @OneToMany(mappedBy = "espaceTravail")
//    private Set<Secretaire> secretaires = new HashSet<>();

//    // Ajouter ces méthodes
//    public Set<Secretaire> getSecretaires() {
//        return secretaires;
//    }

//    public void addSecretaire(Secretaire secretaire) {
//        secretaires.add(secretaire);
//        secretaire.setEspaceTravail(this);
//    }

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



//    public Set<Medecin> getMedecins() {
//        return medecins;
//    }
//
//    public void setMedecins(Set<Medecin> medecins) {
//        this.medecins = medecins;
//    }

//    public Set<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Set<Patient> patients) {
//        this.patients = patients;
//    }


    @Override
    public String toString() {
        return "EspaceTravail{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}