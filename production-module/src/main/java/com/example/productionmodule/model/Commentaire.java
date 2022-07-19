package com.example.productionmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
public class Commentaire {
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    String date_comment = formatter.format(new Date()) ;
    private Long userId;
    private Long productionId;

    public Commentaire() {
    }

    public Commentaire(String text, Long userId, Long productionId) {
        this.text = text;
        this.userId = userId;
        this.productionId = productionId;
    }

}
