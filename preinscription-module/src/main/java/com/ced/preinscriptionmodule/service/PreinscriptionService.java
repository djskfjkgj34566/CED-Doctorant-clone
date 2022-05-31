package com.ced.preinscriptionmodule.service;

import com.ced.preinscriptionmodule.model.Preinscription;
import com.ced.preinscriptionmodule.repository.PreinscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreinscriptionService {

    @Autowired
    private PreinscriptionRepo preinscriptionRepo;


    public int save(Preinscription preinscription){
        if(preinscriptionRepo.findByCin(preinscription.getCin()) == null){
            preinscriptionRepo.save(preinscription);
            return  1;
        }
        return -1;
    }

    public int update(Preinscription preinscription){
        if(preinscriptionRepo.findByCin(preinscription.getCin())==null){
            System.out.println("method update in preinscription ms called successfully");
            return -1;
        }
        System.out.println("method update in preinscription ms called successfully");
        preinscriptionRepo.save(preinscription);
        return 1;
    }

    public List<Preinscription> getAll() {
        return preinscriptionRepo.findAll(Sort.by(Sort.Direction.ASC, "nom"));
    }

    public Preinscription getByCin(String cin){
        return preinscriptionRepo.findByCin(cin);
    }

    public Long countData() {
        return preinscriptionRepo.countNumberOfData();
    }
}
