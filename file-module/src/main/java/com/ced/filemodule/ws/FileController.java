package com.ced.filemodule.ws;

import com.ced.filemodule.dto.FichierUpdateDto;
import com.ced.filemodule.model.Fichier;
import com.ced.filemodule.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/ms-file")
@CrossOrigin("http://localhost:3000")
public class FileController {

    @Autowired
    private FileService fileService;

//    @PostMapping("/")
    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Fichier save(@RequestBody MultipartFile file) throws IOException {
        System.out.println("the multipart file is the folowing : "+file);
        return fileService.save(file);
    }

    @GetMapping("/binaire/{name}")
    public ResponseEntity<InputStreamResource> getFileFromFolder(@PathVariable String name) throws FileNotFoundException {
        return fileService.get(name);
    }
    @GetMapping("/object/{id}")
    public ResponseEntity<Fichier> get(@PathVariable Long id) throws FileNotFoundException {
        return new ResponseEntity<>(fileService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public int update(@ModelAttribute FichierUpdateDto dto) throws IOException {
        return fileService.update(dto);
    }

    @DeleteMapping("name/{name}")
    public int delete(@PathVariable String name){
        return fileService.delete(name);
    }

    @DeleteMapping("id/{id}")
    public int delete(@PathVariable Long id){
        return fileService.deleteById(id);
    }


}