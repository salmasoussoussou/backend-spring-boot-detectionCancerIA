package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.UserInfo;
import com.MEDCIN.g04.models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByMedecin(Medecin medecin);
    boolean existsByEmail(String email);
}