package com.example.usermodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class ResponsableFD extends User{

    private String code;

}
