package com.example.productionmodule.dto;

import com.example.productionmodule.model.FormationInterneAdmin;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class FormationInternDoctorantParams {

    private Long id;
    private Long userId;
    private Long fichierId;
    private MultipartFile file;
    private Long formationInterneAdminId;
    private String statusFormation;


}
