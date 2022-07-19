package com.example.productionmodule.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor
public class FormationInternAdminParams {

    private Long id;
    private String titre;
    private String Description;
    private String date_debut;
    private String date_fin;
    private Long supportId;
    private MultipartFile file;

}
