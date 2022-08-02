package com.example.productionmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor @NoArgsConstructor
public class FichierUpdateDto {

    private Long id;
    private String oldName;
    private MultipartFile file;
}
