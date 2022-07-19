package com.example.usermodule.ws;

import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.Encadrant;
import com.example.usermodule.model.ResponsableCED;
import com.example.usermodule.service.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ms-user/encadrant")
public class EncadrantController {

    @Autowired
    private EncadrantService encadrantService;

    @PostMapping("/")
    public int save(@RequestBody Encadrant encadrant) throws NoSuchAlgorithmException {
        return encadrantService.save(encadrant);
    }

    @GetMapping("/vkey/{vkey}")
    public Encadrant getByVkey(@PathVariable String vkey) {
        return encadrantService.findByVkey(vkey);
    }

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

    @PostMapping("/set/password")
    public int setRCEDPassword(@RequestBody PasswordDto passwordDto) {
        return encadrantService.setEncadrantPassword(passwordDto);
    }
}
