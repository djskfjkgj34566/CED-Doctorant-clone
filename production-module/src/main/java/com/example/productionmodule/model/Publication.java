package com.example.productionmodule.model;

import com.example.productionmodule.dto.FichierDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Publication extends Production {

    private String titre;
    private String anne;
    private String vIpages;
    private String NomCRScientifique;
    private String auteurs;
    private String BDindexee;
    private String category;

    public Publication(String titre, String anne, String vIpages, String nomCRScientifique, String auteurs, String BDindexee, String category, Long documentId, Long userId) {
        super(null, userId, documentId);
        this.titre = titre;
        this.anne = anne;
        this.vIpages = vIpages;
        this.NomCRScientifique = nomCRScientifique;
        this.auteurs = auteurs;
        this.BDindexee = BDindexee;
        this.category = category;
    }

    public Publication(Long id,String titre, String anne, String vIpages, String nomCRScientifique, String auteurs, String BDindexee, String category, Long documentId, Long userId) {
        super(id, userId, documentId);
        this.titre = titre;
        this.anne = anne;
        this.vIpages = vIpages;
        this.NomCRScientifique = nomCRScientifique;
        this.auteurs = auteurs;
        this.BDindexee = BDindexee;
        this.category = category;
    }
    /*
    public Publication(String titre, String anne, Long documentId, Long userId) throws ParseException {
        super(null, userId, documentId);
        this.titre = titre;
        this.anne = anne;
    }

    public Publication(Long id, String titre, String anne, String category, Long documentId, Long userId) throws ParseException {
        super(id, userId, documentId);
        this.titre = titre;
        this.category = category;
        this.anne = anne;
    }
*/

}