package com.ced.filemodule.repository;

import com.ced.filemodule.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<Fichier, Long> {

    public Fichier findByName(String name);
}
