package com.example.productionmodule.service;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import com.example.productionmodule.dto.FormationInternDoctorantParams;
import com.example.productionmodule.feign.FeignService;
import com.example.productionmodule.model.FormationInterneAdmin;
import com.example.productionmodule.model.FormationInterneDoctorant;
import com.example.productionmodule.repository.FormationInterneDoctorantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormationInterneDoctorantService {

    @Autowired
    private FormationInterneDoctorantRepo repo;
    @Autowired
    private FeignService fileService;

    private FormationInterneAdminService formationIAService;


    public void setFormationIAService(FormationInterneAdminService formationIAService) {
        this.formationIAService = formationIAService;
    }

    public FormationInterneAdminService getFormationIAService() {
        return formationIAService;
    }

    public int save(FormationInternDoctorantParams param){
        FormationInterneDoctorant formation = new FormationInterneDoctorant(
                null,
                param.getUserId(),
                null,
                param.getFormationInterneAdminId(),
                //param.getStatusFormation()
                "encours"
        );
        if(param.getFile()!=null){
        MultipartFile file = param.getFile();
        FichierDto fichier = fileService.save(file);
        formation.setFichierId(fichier.getId());}
        repo.save(formation);
        return 1;
    }

    public FormationInterneDoctorant findById(Long id){
        if(!repo.existsById(id)) return null;
        FormationInterneDoctorant formation = repo.findById(id).get();
        if(formation.getFichierId()!=null){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            formation.setFichier(fichier);
        }
        return formation;
    }

    public List<FormationInterneDoctorant> findAll(){
        List<FormationInterneDoctorant> formations  = repo.findAll();
        List<FormationInterneDoctorant> newFormations = new ArrayList<>();
        for (FormationInterneDoctorant formation: formations){
            if(formation.getFichierId()!=null){
                FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
                formation.setFichier(fichier);
            }
            FormationInterneAdmin formationIA = formationIAService.findById(formation.getFormationInterneAdminId());
            System.out.println(formationIA.toString());
            formation.setFormationInterneAdmin(formationIA);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public List<FormationInterneDoctorant> findAllByUser(Long userId){
        List<FormationInterneDoctorant> formations= repo.findByUserId(userId);
        List<FormationInterneDoctorant> newFormations = new ArrayList<>();
        for (FormationInterneDoctorant formation: formations){
            if(formation.getFichierId()!=null){
                FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
                formation.setFichier(fichier);
            }
            FormationInterneAdmin formationIA = formationIAService.findById(formation.getFormationInterneAdminId());
            System.out.println(formationIA.toString());
            formation.setFormationInterneAdmin(formationIA);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public int update(FormationInterneDoctorant formation, MultipartFile file){
        if(formation.getId()==null || !repo.existsById(formation.getId())) return -1;
        if(formation.getFichierId()!=null){
        FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
        FichierUpdateDto fichierDto = new FichierUpdateDto(formation.getFichierId(), fichier.getName(), file);
        FormationInterneDoctorant fid = new FormationInterneDoctorant();
        fid.setStatusFormation("suivie");
        fileService.update(fichierDto);}
        else{
            FichierDto fichier = fileService.save(file);
            formation.setFichierId(fichier.getId());
        }
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
