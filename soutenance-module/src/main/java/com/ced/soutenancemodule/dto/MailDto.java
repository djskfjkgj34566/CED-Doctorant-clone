package com.ced.soutenancemodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MailDto {

    private String subject;
    private String message;
    private String toEmailAddress;
    private MultipartFile[] files;
}
