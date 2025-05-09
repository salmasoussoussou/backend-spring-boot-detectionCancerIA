package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.Medecin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    boolean existsByEmail(String email);
    Optional<Medecin> findByEmail(String email);
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si besoin
}