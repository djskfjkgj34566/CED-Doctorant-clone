package com.example.usermodule.Repository;

import com.example.usermodule.model.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantRepo extends JpaRepository<Encadrant, Long> {
}
