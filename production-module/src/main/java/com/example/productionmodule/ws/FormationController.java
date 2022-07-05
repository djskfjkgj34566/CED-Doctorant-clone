package com.example.productionmodule.ws;

import com.example.productionmodule.dto.FormationSaveParam;
import com.example.productionmodule.model.Formation;
import com.example.productionmodule.model.Publication;
import com.example.productionmodule.service.FormationService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ms-production/formation")
public class FormationController {

    @Autowired
    private FormationService service;

    @PostMapping("/")
    public int save(@ModelAttribute FormationSaveParam param) throws IOException, ParseException {
        return service.save(param);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Formation>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> get(@PathVariable Long id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }
    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<List<Formation>> getAllByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(service.getAllByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/one/userId/{userId}/id/{id}")
    public ResponseEntity<Formation> getOneByUserId(@PathVariable Long userId, @PathVariable Long id){
        return new ResponseEntity<>(service.getOneByUserIdAndId(userId, id), HttpStatus.OK);
    }

    @PutMapping("/")
    public int update(@ModelAttribute FormationSaveParam param) throws ParseException {
        Formation formation = new Formation(
                param.getId(),
                param.getTitre(),
                param.getDescription(),
                new SimpleDateFormat("dd/MM/yyyy").parse(param.getDate_debut()),
                new SimpleDateFormat("dd/MM/yyyy").parse(param.getDate_fin()),
                param.getSupportId(),
                param.getUserId()
        );
        return service.update(formation, param.getFile());
    }

    @DeleteMapping("/{id}")
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
