package com.example.productionmodule.ws;

import com.example.productionmodule.dto.SaveParam;
import com.example.productionmodule.model.Communication;
import com.example.productionmodule.model.Publication;
import com.example.productionmodule.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ms-production/communication")
public class CommunicationController {

    @Autowired
    private CommunicationService service;

//    @PostMapping("/")
    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public int save(@ModelAttribute SaveParam param) throws IOException, ParseException {
        System.out.println("la method save de rest api return la vavaeur suivant :"+param.toString());
        return service.save(param);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Communication>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<List<Communication>> getAllByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(service.getAllByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/one/userId/{userId}/id/{id}")
    public ResponseEntity<Communication> getOneByUserIdAndId(@PathVariable Long userId, @PathVariable Long id){
        return new ResponseEntity<>(service.getOneByUserIdAndId(userId, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Communication> get(@PathVariable Long id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping("/file/{communicationId}")
    public ResponseEntity<InputStreamResource> getCommunicationFile(@PathVariable Long communicationId) throws IOException {
        return service.getCommunicationFile(communicationId);
    }

    @PutMapping("/")
    public int update(@ModelAttribute SaveParam param) throws ParseException {
        return service.update(param);
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
