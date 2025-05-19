package com.MEDCIN.g04.services;

import com.MEDCIN.g04.models.AnalyseAI;

import java.util.List;

public interface AnalyseAIService {

    // Enregistre une nouvelle analyse IA liée à une image médicale
    AnalyseAI saveAnalyse(AnalyseAI analyse);

    // Récupère la liste de toutes les analyses enregistrées
    List<AnalyseAI> getAllAnalyses();

    // Récupère une analyse par son identifiant
    AnalyseAI getAnalyseById(Long id);

    // Supprime une analyse par son identifiant
    void deleteAnalyse(Long id);

    // Récupère une analyse en fonction de l'image médicale associée
    AnalyseAI getAnalyseByImageId(Long imageId);
    long countAnalyses();
}
