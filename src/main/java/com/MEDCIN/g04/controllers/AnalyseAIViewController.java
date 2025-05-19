package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.AnalyseAI;
import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.services.AnalyseAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/analyseai-view") // <-- évite le conflit
public class AnalyseAIViewController {
    private final AnalyseAIService analyseaiService;

    @Autowired
    public AnalyseAIViewController(AnalyseAIService analyseaiService){
        this.analyseaiService = analyseaiService;
    }
    @GetMapping
    public String listAnalyseAI(Model model){
        List<AnalyseAI> analyseai = analyseaiService.getAllAnalyses();
        model.addAttribute("analyseai", analyseai);
        return "analyseai/list";
    }
}
