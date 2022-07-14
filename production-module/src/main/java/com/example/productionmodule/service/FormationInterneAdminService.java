package com.example.productionmodule.service;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import com.example.productionmodule.dto.FormationInternAdminParams;
import com.example.productionmodule.feign.FeignService;
import com.example.productionmodule.model.Formation;
import com.example.productionmodule.model.FormationInterneAdmin;
import com.example.productionmodule.model.FormationInterneDoctorant;
import com.example.productionmodule.repository.FormationInterneAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationInterneAdminService {

    @Autowired
    private FormationInterneAdminRepo repo;
    @Autowired
    private FeignService fileService;
    @Autowired
    private FormationInterneDoctorantService formationInterneDoctorantService;

    @PostConstruct
    public void init() {
        formationInterneDoctorantService.setFormationIAService(this);
    }

    public List<FormationInterneAdmin> findAll(){
        List<FormationInterneAdmin> formations  = repo.findAll();

       List<FormationInterneAdmin> newFormations = new ArrayList<>();
        for (FormationInterneAdmin formation: formations){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            formation.setFichier(fichier);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public List<FormationInterneAdmin> findAllNotSelected(Long userId){
        List<FormationInterneAdmin> formations  = repo.findAll();
        List<FormationInterneDoctorant> formationInterneDoctorantList = formationInterneDoctorantService.findAllByUser(userId);
        List<FormationInterneAdmin> formationsChoisie  = new ArrayList<>();
        List<FormationInterneAdmin> result = new ArrayList<>();
        for (FormationInterneDoctorant formation:formationInterneDoctorantList) {
            formationsChoisie.add(formation.getFormationInterneAdmin());
        }
        result = formations.stream().filter(formation->!formationsChoisie.contains(formation)).collect(Collectors.toList());
        List<FormationInterneAdmin> newFormations = new ArrayList<>();
        for (FormationInterneAdmin formation: result){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            formation.setFichier(fichier);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public FormationInterneAdmin findById(Long id){
        if(!repo.existsById(id)) return null;
        FormationInterneAdmin formation = repo.findById(id).get();
        FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
        formation.setFichier(fichier);
        return formation;
    }

    public int save(FormationInternAdminParams param) throws ParseException {
        if(!param.getFile().isEmpty()) {
            System.out.println("l'objet param est le suivant : "+param.toString());
            FormationInterneAdmin formation = new FormationInterneAdmin(
                    null,
                    param.getTitre(),
                    param.getDescription(),
                    new SimpleDateFormat("dd/MM/yyyy").parse(param.getDate_debut()),
                    new SimpleDateFormat("dd/MM/yyyy").parse(param.getDate_fin()),
                    null
            );
            MultipartFile file = param.getFile();
            FichierDto fichier = fileService.save(file);
            formation.setFichierId(fichier.getId());
            repo.save(formation);
            return 1;
        }
        return -1;
    }

    public int update(FormationInterneAdmin formation, MultipartFile file){
        if(formation.getId()==null || !repo.existsById(formation.getId())) return -1;
        FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
        FichierUpdateDto fichierDto = new FichierUpdateDto(formation.getFichierId(), fichier.getName(), file);
        fileService.update(fichierDto);
        repo.save(formation);
        return 1;
    }

    public int delete(Long id){
        if(!repo.existsById(id)) return -1;
        repo.deleteById(id);
        return 1;
    }

    public Long countAll() {
        return repo.countNumberOfData();
    }
}
