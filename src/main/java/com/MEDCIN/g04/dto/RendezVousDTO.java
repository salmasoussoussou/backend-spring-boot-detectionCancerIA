package com.MEDCIN.g04.dto;

import com.MEDCIN.g04.models.RendezVous;
import java.util.Date;

public class RendezVousDTO {
    private Long id;
    private Date date;
    private String motif;
    private Long patientId;
    private Long secretaireId;

    public RendezVousDTO() {}

    public RendezVousDTO(RendezVous rdv) {
        this.id = rdv.getId();
        this.date = rdv.getDate();
        this.motif = rdv.getMotif();
        this.patientId = (rdv.getPatient() != null) ? rdv.getPatient().getId() : null;
        this.secretaireId = (rdv.getSecretaire() != null) ? rdv.getSecretaire().getId() : null;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getSecretaireId() {
        return secretaireId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setSecretaireId(Long secretaireId) {
        this.secretaireId = secretaireId;
    }
}
