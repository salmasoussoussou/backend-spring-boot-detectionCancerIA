package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.AnalyseAIDTO;
import com.MEDCIN.g04.models.AnalyseAI;
import com.MEDCIN.g04.services.AnalyseAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analyses")
@CrossOrigin(origins = "*")
public class AnalyseAIController {

    private final AnalyseAIService analyseService;

    @Autowired
    public AnalyseAIController(AnalyseAIService analyseService) {
        this.analyseService = analyseService;
    }

    // Ajouter une analyse
    @PostMapping
    public AnalyseAI createAnalyse(@RequestBody AnalyseAI analyse) {
        return analyseService.saveAnalyse(analyse);
    }

    // Récupérer toutes les analyses
    @GetMapping
    public List<AnalyseAIDTO> getAllAnalyses() {
        List<AnalyseAI> analyses = analyseService.getAllAnalyses();
        return analyses.stream()
                .map(AnalyseAIDTO::new)  // Conversion de chaque analyse en AnalyseAIDTO
                .collect(Collectors.toList());
    }

    // Récupérer une analyse par son ID
    @GetMapping("/{id}")
    public AnalyseAIDTO getAnalyseById(@PathVariable Long id) {
        AnalyseAI analyse = analyseService.getAnalyseById(id);
        return analyse != null ? new AnalyseAIDTO(analyse) : null;
    }

    // Récupérer une analyse via l'ID de l'image
    @GetMapping("/image/{imageId}")
    public AnalyseAIDTO getAnalyseByImageId(@PathVariable Long imageId) {
        AnalyseAI analyse = analyseService.getAnalyseByImageId(imageId);
        return analyse != null ? new AnalyseAIDTO(analyse) : null;
    }

    // Supprimer une analyse
    @DeleteMapping("/{id}")
    public void deleteAnalyse(@PathVariable Long id) {
        analyseService.deleteAnalyse(id);
    }
}
