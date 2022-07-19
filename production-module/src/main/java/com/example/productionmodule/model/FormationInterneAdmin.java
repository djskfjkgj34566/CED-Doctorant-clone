package com.example.productionmodule.model;

import com.example.productionmodule.dto.FichierDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class FormationInterneAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    @Column(length = 4000)
    private String description;
    private Date date_debut;
    private Date date_fin;
    private Long fichierId;
    @Transient
    private FichierDto fichier;

    public FormationInterneAdmin(Long id,String titre, String description, Date date_debut, Date date_fin, Long fichierId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.fichierId = fichierId;
    }

}
