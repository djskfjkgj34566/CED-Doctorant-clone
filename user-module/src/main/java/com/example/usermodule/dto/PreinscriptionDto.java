package com.example.usermodule.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PreinscriptionDto {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String address;
    private String specialite;
    private String cin;
    private String date_preinscription = formatter.format(new Date()) ;
    private String status = "non valide";
}
