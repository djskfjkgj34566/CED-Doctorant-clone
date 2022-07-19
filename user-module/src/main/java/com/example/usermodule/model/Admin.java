package com.example.usermodule.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Admin extends User{

    private String teste;
}
