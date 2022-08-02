package com.example.productionmodule.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SaveParam {

    private Long id;
    private String sujet;
    private String description;
    private String date_communication;
    private String auteurs;
    private String typeCom;
    private String manifName;
    private String manifLieu;
    private Long fichierId;
    private Long userId;
    private MultipartFile file;
}
