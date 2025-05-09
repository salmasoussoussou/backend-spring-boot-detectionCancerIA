package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.AnalyseAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyseAIRepository extends JpaRepository<AnalyseAI, Long> {
    AnalyseAI findByImage_Id(Long imageId);
}
