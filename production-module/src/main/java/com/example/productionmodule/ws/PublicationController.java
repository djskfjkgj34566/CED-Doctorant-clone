package com.example.productionmodule.ws;

import com.example.productionmodule.dto.FormationSaveParam;
import com.example.productionmodule.dto.PublicationSaveParam;
import com.example.productionmodule.model.Formation;
import com.example.productionmodule.model.Publication;
import com.example.productionmodule.service.FormationService;
import com.example.productionmodule.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ms-production/publication")
public class PublicationController {


    @Autowired
    private PublicationService service;

    @PostMapping("/")
    public int save(@ModelAttribute PublicationSaveParam param) throws IOException, ParseException {
        System.out.println("l'objet file est le suivant : "+param.toString());
        return service.save(param);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Publication>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> get(@PathVariable Long id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }
    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<List<Publication>> getAllByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(service.getAllByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/one/userId/{userId}/id/{id}")
    public ResponseEntity<Publication> getOneByUserIdAndId(@PathVariable Long userId, @PathVariable Long id){
        return new ResponseEntity<>(service.getOneByUserIdAndId(userId, id), HttpStatus.OK);
    }

    @PutMapping("/")
    public int update(@ModelAttribute PublicationSaveParam param) throws ParseException {
        Publication publication = new Publication(
                param.getId(),
                param.getTitre(),
                param.getAnne(),
                param.getVIpages(),
                param.getNomCRScientifique(),
                param.getAuteurs(),
                param.getBDindexee(),
                param.getCategory(),
                param.getDocumentId(),
                param.getUserId()
        );
        return service.update(publication, param.getFile());
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/get/count")
    public Long countAll() {
        return service.countAll();
    }
}
