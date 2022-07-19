package com.example.productionmodule.ws;

import com.example.productionmodule.dto.FormationInternDoctorantParams;
import com.example.productionmodule.model.FormationInterneDoctorant;
import com.example.productionmodule.service.FormationInterneDoctorantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/ms-production/formationInterneDoctorant")
public class FormationInternDoctorantController {

    @Autowired
    private FormationInterneDoctorantService service;

    @ResponseBody
    @GetMapping("/id/{id}")
    public FormationInterneDoctorant getById(@PathVariable Long id){
        return service.findById(id);
    }

    @ResponseBody
    @GetMapping("/userId/{userId}")
    public List<FormationInterneDoctorant> getByUser(@PathVariable Long userId){
        return service.findAllByUser(userId);
    }
    @ResponseBody
    @GetMapping("/")
    public List<FormationInterneDoctorant> getAll(){
        return service.findAll();
    }

    @ResponseBody
    @PostMapping("/")
    public int save(@ModelAttribute FormationInternDoctorantParams formation){
        return service.save(formation);
    }

    @ResponseBody
    @PutMapping("/")
    public int update(@ModelAttribute FormationInternDoctorantParams formation){
        FormationInterneDoctorant formationInterneDoctorant = new FormationInterneDoctorant(
                formation.getId(),
                formation.getUserId(),
                formation.getFichierId(),
                formation.getFormationInterneAdminId(),
                "suivie"
        ) ;
        return service.update(formationInterneDoctorant, formation.getFile());
    }

    @ResponseBody
    @DeleteMapping("/id/{id}")
    public int delete(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/get/count")
    public Long countAll() {
        return service.countAll();
    }

    @GetMapping("/get/count/{userId}")
    public Long countAllByUser(@PathVariable Long userId) {
        return service.countAllByUser(userId);
    }
}
