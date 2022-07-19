package com.example.productionmodule.ws;

import com.example.productionmodule.dto.FormationInternAdminParams;
import com.example.productionmodule.model.FormationInterneAdmin;
import com.example.productionmodule.service.FormationInterneAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/api/v1/ms-production/formationInterne")
public class FormationInternAdminController {

    @Autowired
    private FormationInterneAdminService service;
    @ResponseBody
    @GetMapping("/id/{id}")
    public FormationInterneAdmin getOne(@PathVariable Long id){
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping("/userId/{userId}")
    public List<FormationInterneAdmin> getAllNotSelected(@PathVariable Long userId){
        return service.findAllNotSelected(userId);
    }

    @ResponseBody
    @GetMapping("/")
    public List<FormationInterneAdmin> getAll(){
        return service.findAll();
    }

    @ResponseBody
    @PostMapping("/")
    public int save(@ModelAttribute FormationInternAdminParams formation) throws ParseException {
        return service.save(formation);
    }

    @ResponseBody
    @PutMapping("/")
    public int update(@ModelAttribute FormationInternAdminParams formation) throws ParseException {
        FormationInterneAdmin formationInternAdmin = new FormationInterneAdmin(
                formation.getId(),
                formation.getTitre(),
                formation.getDescription(),
                new SimpleDateFormat("dd/MM/yyyy").parse(formation.getDate_debut()),
                new SimpleDateFormat("dd/MM/yyyy").parse(formation.getDate_fin()),
                formation.getSupportId()
        );
        return service.update(formationInternAdmin, formation.getFile());
    }

    @ResponseBody
    @DeleteMapping("/id/{id}")
    public int delete(@PathVariable Long id){
        return service.delete(id);
    }

    @ResponseBody
    @GetMapping("/get/count")
    public Long countAll() {
        return service.countAll();
    }
}
