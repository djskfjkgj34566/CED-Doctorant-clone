package com.example.chatmodule.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class DemandeEncadrement {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 4000)
    private String Body;
    private Long doctorantId;
    private Long encadrantId;

}
