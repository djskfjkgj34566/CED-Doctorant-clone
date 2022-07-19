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
public class Formation extends Production {


    private String titre;
    @Column(length = 4000)
    private String description;
    private Date date_debut;
    private Date date_fin;

    public Formation(String titre, String description, String date_debut, String date_fin, Long supportId, Long userId) throws ParseException {
        super(null, userId, supportId);
        this.titre=titre;
        this.description=description;
        this.date_debut=new SimpleDateFormat("dd/MM/yyyy").parse(date_debut);
        this.date_fin=new SimpleDateFormat("dd/MM/yyyy").parse(date_fin);
    }

    public Formation(Long id, String titre, String description, Date date_debut, Date date_fin, Long supportId, Long userId) throws ParseException {
        super(id, userId, supportId);
        this.titre=titre;
        this.description=description;
        this.date_debut=date_debut;
        this.date_fin=date_fin;

    }

    public Formation(String titre, String description, Date date_debut, Date date_fin, Long supportId, Long userId) {
        super(null, userId, supportId);
        this.titre=titre;
        this.description=description;
        this.date_debut=date_debut;
        this.date_fin=date_fin;
    }
}
