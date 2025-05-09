package com.MEDCIN.g04.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import com.MEDCIN.g04.models.Secretaire;
import com.MEDCIN.g04.services.SecretaireService;
import com.MEDCIN.g04.dto.SecretaireDTO;
import com.MEDCIN.g04.dto.EspaceTravailDTO;

@RestController
@RequestMapping("/secretaires")
public class SecretaireController {

    private final SecretaireService secretaireService;

    @Autowired
    public SecretaireController(SecretaireService secretaireService) {
        this.secretaireService = secretaireService;
    }

    @GetMapping
    public List<SecretaireDTO> getAllSecretaires() {
        return secretaireService.getAllSecretaires().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SecretaireDTO getSecretaireById(@PathVariable Long id) {
        Secretaire secretaire = secretaireService.getSecretaireById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Secrétaire non trouvée"));
        return convertToDTO(secretaire);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SecretaireDTO createSecretaire(@RequestBody Secretaire secretaire) {
        Secretaire saved = secretaireService.createSecretaire(secretaire);
        return convertToDTO(saved);
    }

    @PutMapping("/{id}")
    public SecretaireDTO updateSecretaire(@PathVariable Long id, @RequestBody Secretaire secretaire) {
        Secretaire updated = secretaireService.updateSecretaire(id, secretaire);
        return convertToDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSecretaire(@PathVariable Long id) {
        secretaireService.deleteSecretaire(id);
    }

    private SecretaireDTO convertToDTO(Secretaire secretaire) {
        EspaceTravailDTO espaceDTO = null;

        if(secretaire.getEspaceTravail() != null) {
            espaceDTO = new EspaceTravailDTO(
                    secretaire.getEspaceTravail().getId(),
                    secretaire.getEspaceTravail().getNom(),
                    secretaire.getEspaceTravail().getAdresse()
            );
        }

        return new SecretaireDTO(
                secretaire.getId(),
                secretaire.getNom(),
                secretaire.getPrenom(),
                secretaire.getEmail(),
                espaceDTO
        );
    }
}