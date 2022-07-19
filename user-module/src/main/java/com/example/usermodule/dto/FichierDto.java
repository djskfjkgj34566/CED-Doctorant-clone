package com.example.usermodule.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FichierDto {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Long id;
    private String name;
    private String date_creation = formatter.format(new Date()) ;
}
