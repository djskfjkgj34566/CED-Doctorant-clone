package com.example.chatmodule.service;

import com.example.chatmodule.Repository.DemandeEncadrementRepo;
import com.example.chatmodule.module.DemandeEncadrement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeEncadrementService {

    @Autowired
    private DemandeEncadrementRepo repo;

    public DemandeEncadrement findByEncadrantId(Long id) {
        return repo.findByEncadrantId(id);
    }

    public int save(DemandeEncadrement demande){
        repo.save(demande);
        return 1;
    }
}
