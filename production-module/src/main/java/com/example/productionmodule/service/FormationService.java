package com.example.productionmodule.service;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import com.example.productionmodule.dto.FormationSaveParam;
import com.example.productionmodule.feign.FeignService;
import com.example.productionmodule.model.Formation;
import com.example.productionmodule.repository.FormationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormationService {

    @Autowired
    private FormationRepo repository;
    @Autowired
    private FeignService fileService;

    public int save(FormationSaveParam param) throws IOException, ParseException {
        if(!param.getFile().isEmpty()) {
            System.out.println("l'objet param est le suivant : "+param.toString());
            Formation formation = new Formation(
                    param.getTitre(),
                    param.getDescription(),
                    new SimpleDateFormat("dd/MM/yyyy").parse(param.getDate_debut()),
                    new SimpleDateFormat("dd/MM/yyyy").parse(param.getDate_fin()),
                    null,
                    param.getUserId()
            );
            MultipartFile file = param.getFile();
            FichierDto fichier = fileService.save(file);
            formation.setFichierId(fichier.getId());
            repository.save(formation);
            return 1;
        }
        return -1;
    }

    public List<Formation> getAll(){

        List<Formation> formations = repository.findAll();
        List<Formation> newFormations = new ArrayList<>();
        for (Formation formation: formations){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            formation.setFichier(fichier);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public Formation getOneByUserIdAndId(Long userId, Long id){
        Formation formation = repository.findOneByUserIdAndId(userId, id);
        FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
        formation.setFichier(fichier);
        return formation;
    }

    public List<Formation> getAllByUserId(Long userId){
        List<Formation> formations= repository.findAllByUserId(userId);
        List<Formation> newFormations = new ArrayList<>();
        for (Formation formation: formations){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            formation.setFichier(fichier);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public Formation get(Long id){
        if(!repository.existsById(id)) return null;
        Formation formation = repository.findById(id).get();
        FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
        formation.setFichier(fichier);
        return formation;
    }

    public int update(Formation formation, MultipartFile file) {
        FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
        FichierUpdateDto fichierDto = new FichierUpdateDto(formation.getFichierId(), fichier.getName(), file);
        fileService.update(fichierDto);
        repository.save(formation);
        return 1;
    }

    public int delete(Long id) {
        if(!repository.existsById(id)) return -1;
        Formation formation = repository.getById(id);
        fileService.delete(formation.getFichierId());
        repository.delete(formation);
        return 1;
    }

    public Long countAll() {
        return repository.countNumberOfData();
    }

    public Long countAllByUser(Long userId) {
        return repository.countNumberOfDataByUserId(userId);
    }
}
