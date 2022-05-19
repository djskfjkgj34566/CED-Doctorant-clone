package com.example.productionmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data @AllArgsConstructor
public class FormationSaveParam {

    private Long id;
    private String titre;
    private String Description;
    private String date_debut;
    private String date_fin;
    private Long supportId;
    private MultipartFile file;
    private Long userId;
}