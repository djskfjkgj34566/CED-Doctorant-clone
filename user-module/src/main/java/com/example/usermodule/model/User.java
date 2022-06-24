package com.example.usermodule.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String cin;
    private String password;
    private String vkey;
    @Value("${some.key:false}")
    private boolean enabled;
    @OneToMany
    private List<Authority> authorities;


}
