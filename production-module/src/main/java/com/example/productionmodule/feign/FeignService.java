package com.example.productionmodule.feign;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("file-ms")
public interface FeignService {

//    @PostMapping("/api/v1/ms-file/")
    @RequestMapping(path = "/api/v1/ms-file/", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    FichierDto save(@RequestBody MultipartFile file);


    @GetMapping("/api/v1/ms-file/object/{id}")
    ResponseEntity<FichierDto> getFichier(@PathVariable Long id);

    @GetMapping("/api/v1/ms-file/binaire/{name}")
    ResponseEntity<InputStreamResource> getFileFromFolder(@PathVariable String name);

//    @PutMapping("/api/v1/ms-file/")
    @RequestMapping(path = "/api/v1/ms-file/", method = RequestMethod.PUT, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    int update(@ModelAttribute FichierUpdateDto fichier);

    @DeleteMapping("/api/v1/ms-file/id/{id}")
    int delete(@PathVariable Long id);
}
