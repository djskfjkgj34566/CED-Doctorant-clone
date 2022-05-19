package com.example.usermodule.service;

import com.example.usermodule.Repository.ResponsableCEDRepo;
import com.example.usermodule.model.ResponsableCED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RCEDService {

    @Autowired
    private ResponsableCEDRepo responsableCEDRepo;

    public List<ResponsableCED> findAll() {
        return responsableCEDRepo.findAll();
    }

    public Optional<ResponsableCED> findById(Long aLong) {
        return responsableCEDRepo.findById(aLong);
    }
}
