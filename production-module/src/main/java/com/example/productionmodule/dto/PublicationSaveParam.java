package com.example.productionmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor @Data @NoArgsConstructor
public class PublicationSaveParam {

    private Long id;
    @JsonProperty("titre")
    private String titre;
    private String anne;
    private String vIpages;
    private String NomCRScientifique;
    private String auteurs;
    private String BDindexee;
    private String category;
    private Long documentId;
    @JsonProperty("file")
    private MultipartFile file;
    private Long userId;

}
