package com.example.productionmodule.service;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import com.example.productionmodule.dto.PublicationSaveParam;
import com.example.productionmodule.feign.FeignService;
import com.example.productionmodule.model.Publication;
import com.example.productionmodule.repository.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationService {



    @Autowired
    private PublicationRepo repository;
    @Autowired
    private FeignService fileService;

    public int save(PublicationSaveParam param) throws IOException, ParseException {
        if(!param.getFile().isEmpty()) {
            Publication publication = new Publication(
                    null,
                    param.getTitre(),
                    param.getAnne(),
                    param.getVIpages(),
                    param.getNomCRScientifique(),
                    param.getAuteurs(),
                    param.getBDindexee(),
                    param.getCategory(),
                    null,
                    param.getUserId()
            );
            MultipartFile file = param.getFile();
            FichierDto fichier = fileService.save(file);
            publication.setFichierId(fichier.getId());
            repository.save(publication);
            return 1;
        }
        return -1;
    }

    public Publication get(Long id){
        if(!repository.existsById(id)) return null;
        Publication publication = repository.findById(id).get();
        FichierDto fichier = fileService.getFichier(publication.getFichierId()).getBody();
        publication.setFichier(fichier);
        return publication;
    }

    public List<Publication> getAll(){

        List<Publication> publications = repository.findAll();
        List<Publication> newPublications = new ArrayList<>();
        for (Publication publication:publications) {
            FichierDto fichier = fileService.getFichier(publication.getFichierId()).getBody();
            publication.setFichier(fichier);
            newPublications.add(publication);
        }
        return newPublications;
    }

    public Publication getOneByUserIdAndId(Long userId, Long id){
        Publication publication = repository.findOneByUserIdAndId(userId, id);
        FichierDto fichier = fileService.getFichier(publication.getFichierId()).getBody();
        publication.setFichier(fichier);
        return publication;
    }

    public List<Publication> getAllByUserId(Long userId){
        List<Publication> publications = repository.findAllByUserId(userId);
        List<Publication> newPublications = new ArrayList<>();
        for (Publication publication:publications) {
            FichierDto fichier = fileService.getFichier(publication.getFichierId()).getBody();
            publication.setFichier(fichier);
            newPublications.add(publication);
        }
        return newPublications;
    }


    public int update(Publication publication, MultipartFile file) {
        if(!repository.existsById(publication.getId())) return -1;
        FichierDto fichier = fileService.getFichier(publication.getFichierId()).getBody();
        FichierUpdateDto fichierDto = new FichierUpdateDto(publication.getFichierId(), fichier.getName(), file);
        fileService.update(fichierDto);
        repository.save(publication);
        return 1;
    }

    public int delete(Long id) {
        if(!repository.existsById(id)) return -1;
        Publication publication = repository.getById(id);
        fileService.delete(publication.getFichierId());
        repository.delete(publication);
        return 1;
    }

    public Long countAll() {
        return repository.countNumberOfData();
    }
}
