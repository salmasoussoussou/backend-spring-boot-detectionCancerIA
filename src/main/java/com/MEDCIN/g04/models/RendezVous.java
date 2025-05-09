package com.MEDCIN.g04.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rendez_vous")
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(nullable = false)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_secretaire", nullable = false)
    private Secretaire secretaire;

    // Constructeurs
    public RendezVous() {}

    public RendezVous(Date date, String motif, Patient patient, Secretaire secretaire) {
        this.date = date;
        this.motif = motif;
        this.patient = patient;
        this.secretaire = secretaire;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "id=" + id +
                ", date=" + date +
                ", motif='" + motif + '\'' +
                ", patient=" + patient +
                ", secretaire=" + secretaire +
                '}';
    }
}
