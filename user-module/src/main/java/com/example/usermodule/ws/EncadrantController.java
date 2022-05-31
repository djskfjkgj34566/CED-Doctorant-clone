package com.example.usermodule.ws;

import com.example.usermodule.model.Encadrant;
import com.example.usermodule.service.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ms-user/encadrant")
public class EncadrantController {

    @Autowired
    private EncadrantService encadrantService;

    @GetMapping("/")
    public List<Encadrant> findAll() {
        return encadrantService.findAll();
    }

    @GetMapping("/id/{id}")
    public Encadrant findById(@PathVariable Long id) {
        return encadrantService.findById(id).get();
    }

    @GetMapping("/get/count")
    public Long countData() {
        return encadrantService.countData();
    }

    @GetMapping("/find/encadrant/specialite/{specialite}")
    public List<Encadrant> findEncadrantsBySpecialite( @PathVariable String specialite) {
        return encadrantService.findEncadrantsBySpecialite(specialite);
    }
}
