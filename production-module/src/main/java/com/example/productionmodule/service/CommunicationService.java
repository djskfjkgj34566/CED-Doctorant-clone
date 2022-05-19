package com.example.productionmodule.service;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import com.example.productionmodule.dto.SaveParam;
import com.example.productionmodule.feign.FeignService;
import com.example.productionmodule.model.Communication;
import com.example.productionmodule.model.Publication;
import com.example.productionmodule.repository.CommunicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommunicationService {

    @Autowired
    private CommunicationRepo repository;
    @Autowired
    private FeignService fileService;
    @Autowired
    private ServletContext servletContext;


    public int save(SaveParam param) throws IOException, ParseException {
        if(!param.getFile().isEmpty()) {
            Communication communication = new Communication
                    (
                            param.getSujet(),
                            param.getDescription(),
                            param.getDate_communication(),
                            param.getAuteurs(),
                            param.getTypeCom(),
                            param.getManifName(),
                            param.getManifName(),
                            null,
                            param.getUserId()
                    );
            MultipartFile file = param.getFile();
            FichierDto fichier = fileService.save(file);
            communication.setFichierId(fichier.getId());
            repository.save(communication);
            return 1;
        }
        return -1;
    }

    public List<Communication> getAll(){
        return repository.findAll();
    }

    public ResponseEntity<InputStreamResource> getCommunicationFile(Long communicationId) throws IOException {
        Communication communication = get(communicationId);
        String name = communication.getFichier().getName();
        String originaleFileName = fileService.getFileFromFolder(name).getHeaders().getContentDisposition()
                .getFilename();
        MediaType mediaType = fileService.getFileFromFolder(name).getHeaders().getContentType();
        Long fileLength = fileService.getFileFromFolder(name).getHeaders().getContentLength();
        InputStreamResource resource = new InputStreamResource(fileService.getFileFromFolder(name).getBody().getInputStream());
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + originaleFileName)
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(fileLength) //
                .body(resource);
    }
    public List<Communication> getAllByUserId(Long userId){

        List<Communication> communications = repository.findAllByUserId(userId);
        List<Communication> newCommunications = new ArrayList<>();
        for (Communication communication:communications) {
            FichierDto fichier = fileService.getFichier(communication.getFichierId()).getBody();
            communication.setFichier(fichier);
            newCommunications.add(communication);
        }
        return communications;
    }

    public Communication get(Long id){
        Communication communication = repository.findById(id).get();
        FichierDto fichier = fileService.getFichier(communication.getFichierId()).getBody();
        communication.setFichier(fichier);
        return communication;
    }

    public Communication getOneByUserIdAndId(Long userId, Long id){
        Communication communication = repository.findOneByUserIdAndId(userId, id);
        FichierDto fichier = fileService.getFichier(communication.getFichierId()).getBody();
        communication.setFichier(fichier);
        return communication;
    }

    public int update(SaveParam param) throws ParseException {
        Communication communication = new Communication(
                param.getId(),
                param.getSujet(),
                param.getDescription(),
                param.getDate_communication(),
                param.getAuteurs(),
                param.getTypeCom(),
                param.getManifName(),
                param.getManifLieu(),
                param.getFichierId(),
                param.getUserId());
        FichierDto fichier = fileService.getFichier(communication.getFichierId()).getBody();
        FichierUpdateDto fichierDto = new FichierUpdateDto(communication.getFichierId(), fichier.getName(), param.getFile());
        if(fileService.update(fichierDto)==1){
            repository.save(communication);
            return 1;
        }
        return -1;
    }

    public int delete(Long id) {
        if(!repository.existsById(id)) return -1;
        Communication communication = repository.getById(id);
//        FichierDto fichier = fileService.getFichier(communication.getFichierId()).getBody();
        fileService.delete(communication.getFichierId());
        repository.delete(communication);
        return 1;
    }

    public Long countAll() {
        return repository.countNumberOfData();
    }
}
