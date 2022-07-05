package com.example.productionmodule.repository;

import com.example.productionmodule.model.FormationInterneDoctorant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationInterneDoctorantRepo extends JpaRepository<FormationInterneDoctorant, Long> {

    List<FormationInterneDoctorant> findByUserId(Long userId);
    @Query("select count(c) from FormationInterneDoctorant c")
    public Long countNumberOfData();

    Long countNumberOfDataByUserId(Long userId);


}
