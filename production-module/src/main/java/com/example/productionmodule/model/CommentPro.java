package com.example.productionmodule.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CommentPro {
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String text;
    String date_comment = formatter.format(new Date()) ;

    public CommentPro() {
    }

    public CommentPro(Long id, String titre, String text, String date_comment) {
        this.id = id;
        this.titre = titre;
        this.text = text;
        this.date_comment = date_comment;
    }
}
