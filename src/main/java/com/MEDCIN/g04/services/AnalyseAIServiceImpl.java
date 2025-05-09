package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.AnalyseAI;
import com.MEDCIN.g04.models.ImageMedicale;

import com.MEDCIN.g04.repositories.AnalyseAIRepository;
import com.MEDCIN.g04.repositories.ImageMedicaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MEDCIN.g04.services.AnalyseAIService;


import java.util.List;

@Service
public class AnalyseAIServiceImpl implements AnalyseAIService {

    private final AnalyseAIRepository analyseRepo;
    private final ImageMedicaleRepository imageRepo;

    @Autowired
    public AnalyseAIServiceImpl(AnalyseAIRepository analyseRepo, ImageMedicaleRepository imageRepo) {
        this.analyseRepo = analyseRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public AnalyseAI saveAnalyse(AnalyseAI analyse) {
        // Charger l'image complète à partir de l'ID fourni
        Long imageId = analyse.getImage().getId();
        ImageMedicale image = imageRepo.findById(imageId)
                .orElseThrow(() -> new RuntimeException("ImageMedicale non trouvée avec ID : " + imageId));

        // Réassocier l'objet complet à l'analyse
        analyse.setImage(image);

        return analyseRepo.save(analyse);
    }

    @Override
    public List<AnalyseAI> getAllAnalyses() {
        return analyseRepo.findAll();
    }

    @Override
    public AnalyseAI getAnalyseById(Long id) {
        return analyseRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteAnalyse(Long id) {
        analyseRepo.deleteById(id);
    }

    @Override
    public AnalyseAI getAnalyseByImageId(Long imageId) {
        return analyseRepo.findByImage_Id(imageId);
    }

}
