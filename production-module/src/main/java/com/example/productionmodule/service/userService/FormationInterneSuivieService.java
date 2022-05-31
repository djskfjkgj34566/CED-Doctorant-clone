package com.example.productionmodule.service.userService;

import com.example.productionmodule.dto.FichierDto;
import com.example.productionmodule.dto.FichierUpdateDto;
import com.example.productionmodule.dto.FormationInternSuivieDoctorantParams;
import com.example.productionmodule.feign.FeignService;
import com.example.productionmodule.model.FormationInterneAdmin;
import com.example.productionmodule.model.user.FormationInterneSuivie;
import com.example.productionmodule.repository.FormationInterneAdminRepo;
import com.example.productionmodule.repository.userRepository.FormationInterneSuivieRepo;
import com.example.productionmodule.service.FormationInterneAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormationInterneSuivieService {
    @Autowired
    private FormationInterneSuivieRepo repo;

    @Autowired
    private FormationInterneAdminService FormationIAService;
    @Autowired
    private FeignService fileService;

    public int save(FormationInternSuivieDoctorantParams param){
        FormationInterneSuivie formation = new FormationInterneSuivie(
                null,
                param.getUserId(),
                null,
                param.getFormationInterneAdminId()
        );
        if(param.getFile()!=null){
            MultipartFile file = param.getFile();
            FichierDto fichier = fileService.save(file);
            formation.setFichierId(fichier.getId());}
        repo.save(formation);
        return 1;
    }

    public FormationInterneSuivie findById(Long id){
        if(!repo.existsById(id)) return null;
        FormationInterneSuivie formation = repo.findById(id).get();
        if(formation.getFichierId()!=null){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            formation.setFichier(fichier);
        }
        return formation;
    }

    public List<FormationInterneSuivie> findAll(){
       // List<FormationInterneSuivie> formations1  = FormationIAService.findById();

        List<FormationInterneSuivie> formations  = repo.findAll();
        List<FormationInterneSuivie> newFormations = new ArrayList<>();
        for (FormationInterneSuivie formation: formations){
            if(formation.getFichierId()!=null){
                FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
                formation.setFichier(fichier);
            }
            newFormations.add(formation);
        }
        return newFormations;
    }

    public List<FormationInterneSuivie> findAllByUser(Long userId){
        List<FormationInterneSuivie> formations= repo.findByUserId(userId);
        List<FormationInterneSuivie> newFormations = new ArrayList<>();
        for (FormationInterneSuivie formation: formations){
            if(formation.getFichierId()!=null){
                FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
                formation.setFichier(fichier);
            }
            newFormations.add(formation);
        }
        return newFormations;
    }

    public List<FormationInterneSuivie> findAllFormationsSuiviesByUser(Long userId){
        List<FormationInterneSuivie> formations= repo.findAllByUserId(userId);
        List<FormationInterneSuivie> newFormations = new ArrayList<>();
        for (FormationInterneSuivie formation: formations){
            if(formation.getFichierId()!=null){
                FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
                formation.setFichier(fichier);
            }
            FormationInterneAdmin formationIA = FormationIAService.findById(formation.getFormationInterneAdminId());
            formation.setFormationInterneAdmin(formationIA);
            newFormations.add(formation);
        }
        return newFormations;
    }

    public int update(FormationInterneSuivie formation, MultipartFile file){
        if(formation.getId()==null || !repo.existsById(formation.getId())) return -1;
        if(formation.getFichierId()!=null){
            FichierDto fichier = fileService.getFichier(formation.getFichierId()).getBody();
            FichierUpdateDto fichierDto = new FichierUpdateDto(formation.getFichierId(), fichier.getName(), file);
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
