package com.example.productionmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class FormationInternSuivieDoctorantParams {

    private Long id;
    private Long userId;
    private Long fichierId;
    private MultipartFile file;
    private Long formationInterneAdminId;
}
