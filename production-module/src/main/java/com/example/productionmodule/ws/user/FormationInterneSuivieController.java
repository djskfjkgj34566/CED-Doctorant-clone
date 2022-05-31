package com.example.productionmodule.ws.user;

import com.example.productionmodule.dto.FormationInternDoctorantParams;
import com.example.productionmodule.dto.FormationInternSuivieDoctorantParams;
import com.example.productionmodule.model.user.FormationInterneSuivie;
import com.example.productionmodule.service.userService.FormationInterneSuivieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/ms-production/formationInterneSuivie/valider")
public class FormationInterneSuivieController {
    @Autowired
    private FormationInterneSuivieService service;

    @ResponseBody
    @GetMapping("/id/{id}")
    public FormationInterneSuivie getById(@PathVariable Long id){
        return service.findById(id);
    }



    @ResponseBody
    @GetMapping("/userId/{userId}")
    public List<FormationInterneSuivie> getByUser(@PathVariable Long userId){
        return service.findAllFormationsSuiviesByUser(userId);
    }
    /*@ResponseBody
    @GetMapping("/userId/{userId}")
    public List<FormationInterneSuivie> getByUser(@PathVariable Long userId){
        return service.findAllByUser(userId);
    }*/
    @ResponseBody
    @GetMapping("/")
    public List<FormationInterneSuivie> getAll(){
        return service.findAll();
    }

    @ResponseBody
    @PostMapping("/")
    public int save(@ModelAttribute FormationInternSuivieDoctorantParams formation){
        return service.save(formation);
    }

    @ResponseBody
    @PutMapping("/")
    public int update(@ModelAttribute FormationInternDoctorantParams formation){
        FormationInterneSuivie formationInterneSuivie = new FormationInterneSuivie(
                formation.getId(),
                formation.getUserId(),
                formation.getFichierId(),
                formation.getFormationInterneAdminId()
        ) ;
        return service.update(formationInterneSuivie, formation.getFile());
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
}
