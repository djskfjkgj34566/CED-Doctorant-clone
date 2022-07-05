package com.example.productionmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
/*
    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Comment {
        private Long userId;
        private String username;
        private String text;
        private LocalDateTime timestamp;

    }
    */
}
