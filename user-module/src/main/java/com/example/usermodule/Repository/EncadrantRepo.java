package com.example.usermodule.Repository;

import com.example.usermodule.model.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncadrantRepo extends JpaRepository<Encadrant, Long> {

    @Query("select count(c) from Encadrant c")
    public Long countNumberOfData();

    public List<Encadrant> findEncadrantsBySpecialite(String specialite);
    Encadrant findByVkey(String vkey);
    Encadrant findByCin(String cin);
    public boolean existsByCin(String cin);
}
