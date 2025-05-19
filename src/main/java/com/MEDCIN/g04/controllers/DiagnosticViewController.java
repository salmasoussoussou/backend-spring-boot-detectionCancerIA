package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.Diagnostic;
import com.MEDCIN.g04.services.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/diagnostic-view")
public class DiagnosticViewController {
    private final DiagnosticService diagnosticService;


    @Autowired
    public DiagnosticViewController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }
    @GetMapping
    public String listDiagnostic(Model model) {
        List<Diagnostic> diagnostics = diagnosticService.getAllDiagnostics();
        model.addAttribute("diagnostics", diagnostics);
        return "diagnostic/list";
    }

}
