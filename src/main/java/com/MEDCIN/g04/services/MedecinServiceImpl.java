package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.models.UserInfo;
import com.MEDCIN.g04.repositories.MedecinRepository;
import com.MEDCIN.g04.repositories.UserInfoRepository;
import com.MEDCIN.g04.services.MedecinService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinServiceImpl implements MedecinService {

    private final MedecinRepository medecinRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MedecinServiceImpl(MedecinRepository medecinRepository,
                              UserInfoRepository userInfoRepository,
                              PasswordEncoder passwordEncoder) {
        this.medecinRepository = medecinRepository;
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Medecin saveMedecin(Medecin medecin) {
        if (medecinRepository.existsByEmail(medecin.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        if (medecin.getPassword() == null || medecin.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide.");
        }

        medecin.setPassword(passwordEncoder.encode(medecin.getPassword()));
        Medecin savedMedecin = medecinRepository.save(medecin);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(savedMedecin.getEmail());
        userInfo.setPassword(savedMedecin.getPassword());
        userInfo.setRole(UserInfo.Role.MEDECIN);
        userInfo.setMedecin(savedMedecin);
        userInfo.setEnabled(true); // ✅ Ajout nécessaire pour éviter l’erreur
        userInfoRepository.save(userInfo);
        return savedMedecin;
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Optional<Medecin> getMedecinById(Long id) {
        return medecinRepository.findById(id);
    }

    @Override
    @Transactional
    public Medecin updateMedecin(Long id, Medecin medecinDetails) {
        return medecinRepository.findById(id)
                .map(medecin -> {
                    // Mise à jour des champs simples
                    if (medecinDetails.getNom() != null) {
                        medecin.setNom(medecinDetails.getNom());
                    }
                    if (medecinDetails.getPrenom() != null) {
                        medecin.setPrenom(medecinDetails.getPrenom());
                    }
                    if (medecinDetails.getEspaceTravail() != null) {
                        medecin.setEspaceTravail(medecinDetails.getEspaceTravail());
                    }

                    // Mise à jour de l'email
                    if (medecinDetails.getEmail() != null &&
                            !medecinDetails.getEmail().equals(medecin.getEmail())) {

                        if (medecinRepository.existsByEmail(medecinDetails.getEmail())) {
                            throw new IllegalArgumentException("Le nouvel email est déjà utilisé");
                        }
                        medecin.setEmail(medecinDetails.getEmail());
                        updateUserInfoEmail(medecin, medecinDetails.getEmail());
                    }

                    // Mise à jour du mot de passe
                    if (medecinDetails.getPassword() != null &&
                            !medecinDetails.getPassword().isEmpty()) {

                        String encodedPassword = passwordEncoder.encode(medecinDetails.getPassword());
                        medecin.setPassword(encodedPassword);
                        updateUserInfoPassword(medecin, encodedPassword);
                    }

                    return medecinRepository.save(medecin);
                })
                .orElseThrow(() -> new EntityNotFoundException("Médecin non trouvé avec l'id: " + id));
    }

    private void updateUserInfoEmail(Medecin medecin, String newEmail) {
        userInfoRepository.findByMedecin(medecin)
                .ifPresent(userInfo -> {
                    userInfo.setEmail(newEmail);
                    userInfoRepository.save(userInfo);
                });
    }

    private void updateUserInfoPassword(Medecin medecin, String newPassword) {
        userInfoRepository.findByMedecin(medecin)
                .ifPresent(userInfo -> {
                    userInfo.setPassword(newPassword);
                    userInfoRepository.save(userInfo);
                });
    }

    @Override
    @Transactional
    public void deleteMedecin(Long id) {
        medecinRepository.findById(id).ifPresent(medecin -> {
            userInfoRepository.findByMedecin(medecin)
                    .ifPresent(userInfoRepository::delete);
            medecinRepository.delete(medecin);
        });
    }

    @Override
    public Optional<Medecin> findByEmail(String email) {
        return medecinRepository.findByEmail(email);
    }
}
