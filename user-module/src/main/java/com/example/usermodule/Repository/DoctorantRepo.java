package com.example.usermodule.Repository;

import com.example.usermodule.model.Doctorant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorantRepo extends JpaRepository<Doctorant,Long> {

    Doctorant findByCin(String cin);
    Doctorant findByVkey(String vkey);
    @Query("select count(c) from Doctorant c")
    Long countNumberOfData();

}
