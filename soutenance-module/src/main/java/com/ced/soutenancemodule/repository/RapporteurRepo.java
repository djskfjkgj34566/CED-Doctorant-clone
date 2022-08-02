package com.ced.soutenancemodule.repository;

import com.ced.soutenancemodule.model.Rapporteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RapporteurRepo extends JpaRepository<Rapporteur, Long> {

    int countByDoctorantId(Long id);
    List<Rapporteur> findByDoctorantId(Long id);

}
