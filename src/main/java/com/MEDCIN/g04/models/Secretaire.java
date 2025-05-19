package com.MEDCIN.g04.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "secretaires")
public class Secretaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // Relation ManyToOne avec EspaceTravail
    @ManyToOne(fetch = FetchType.EAGER) // Charge toujours la relation
    @JoinColumn(name = "espace_travail_id")
    private EspaceTravail espaceTravail;
@OneToMany(mappedBy = "secretaire")
@JsonIgnore
private List<RendezVous> rendezVousList;


    // Constructeurs
    public Secretaire() {}

    public Secretaire(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    // Getters et Setters
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

    public EspaceTravail getEspaceTravail() {
        return espaceTravail;
    }

    public void setEspaceTravail(EspaceTravail espaceTravail) {
        this.espaceTravail = espaceTravail;
    }

//    public Set<RendezVous> getRendezVous() {
//        return rendezVous;
//    }
//
//    public void setRendezVous(Set<RendezVous> rendezVous) {
//        this.rendezVous = rendezVous;
//    }

    @Override
    public String toString() {
        return "Secretaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", espaceTravail=" + (espaceTravail != null
                ? "{" +
                "id=" + espaceTravail.getId() +
                ", nom='" + espaceTravail.getNom() + '\'' +
                ", adresse='" + espaceTravail.getAdresse() + '\'' +
                "}"
                : "N/A") +
                '}';
    }
}
