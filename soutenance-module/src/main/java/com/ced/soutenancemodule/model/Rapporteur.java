package com.ced.soutenancemodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class Rapporteur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomComplet;
    private String grade;
    private String specialite;
    private String laboratoir;
    private String institution;
    private String email;
    private String adresse;
    private String tel;
    private Long doctorantId;
}
