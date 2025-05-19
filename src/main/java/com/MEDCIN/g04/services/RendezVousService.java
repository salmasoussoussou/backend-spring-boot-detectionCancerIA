package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.RendezVous;
import java.util.List;

public interface RendezVousService {
    RendezVous saveRendezVous(RendezVous rdv);
    List<RendezVous> getAllRendezVous();
    RendezVous getRendezVousById(Long id);
    void deleteRendezVous(Long id);
    long countRendezVous();
}
