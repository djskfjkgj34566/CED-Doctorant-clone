package com.example.usermodule.service;

import com.example.usermodule.Repository.EncadrantRepo;
import com.example.usermodule.model.Encadrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncadrantService {

    @Autowired
    private EncadrantRepo encadrantRepo;


    public List<Encadrant> findAll() {
        return encadrantRepo.findAll();
    }

    public Optional<Encadrant> findById(Long id) {
        System.out.println("la valeur de l'd envoyer par le front end est : "+id);
        return encadrantRepo.findById(id);
    }

    public Long countData() {
        return encadrantRepo.countNumberOfData();
    }

    public List<Encadrant> findEncadrantsBySpecialite(String specialite) {
        return encadrantRepo.findEncadrantsBySpecialite(specialite);
    }
}
