package com.MEDCIN.g04.repositories;

import com.MEDCIN.g04.models.EspaceTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspaceTravailRepository extends JpaRepository<EspaceTravail, Long> {

}