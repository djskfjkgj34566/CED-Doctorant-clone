package com.example.productionmodule.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class FichierDto {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Long id;
    private String name;
//    private String image_name;
    private String date_creation = formatter.format(new Date()) ;
}
