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
public class Communication extends Production{

    private String sujet;
    @Column(length = 4000)
    private String description;
    private Date date_communication;
    private String auteurs;
    private String typeCom;
    private String manifName;
    private String manifLieu;


    /*public Communication(String sujet, String description, String date_communication, Long fichierId, Long userID) throws ParseException {
        super(null, userID, fichierId);
        this.sujet = sujet;
        this.description = description;
        this.date_communication = new SimpleDateFormat("dd/MM/yyyy").parse(date_communication);
    }*/
    /*-------------------------------***********************---------------------------------------*/
    public Communication(String sujet, String description, String date_communication,String auteurs,String typeCom,String manifName,String manifLieu, Long fichierId, Long userID) throws ParseException {
        super(null, userID, fichierId);
        this.sujet = sujet;
        this.description = description;
        this.date_communication = new SimpleDateFormat("dd/MM/yyyy").parse(date_communication);
        this.auteurs = auteurs;
        this.typeCom = typeCom;
        this.manifName = manifName;
        this.manifLieu = manifLieu;
    }
    public Communication(Long id, String sujet, String description, String date_communication,String auteurs,String typeCom,String manifName,String manifLieu, Long fichierId, Long userID) throws ParseException {
        super(id, userID, fichierId);
        this.sujet = sujet;
        this.description = description;
        this.date_communication = new SimpleDateFormat("dd/MM/yyyy").parse(date_communication);
        this.auteurs = auteurs;
        this.typeCom = typeCom;
        this.manifName = manifName;
        this.manifLieu = manifLieu;
    }
    /*-------------------------------***********************---------------------------------------*/
   /* public Communication(Long id, String sujet, String description, String date_communication, Long fichierId, Long userID) throws ParseException {
        super(id, userID, fichierId);
        this.sujet = sujet;
        this.description = description;
        this.date_communication = new SimpleDateFormat("dd/MM/yyyy").parse(date_communication);
    }
*/
}
