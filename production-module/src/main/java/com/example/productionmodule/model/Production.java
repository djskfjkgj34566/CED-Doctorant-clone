package com.example.productionmodule.model;

import com.example.productionmodule.dto.FichierDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long fichierId;
    @Transient
    private FichierDto fichier;

    public Production(Long id, Long userId, Long fichierId) {
        this.id = id;
        this.userId = userId;
        this.fichierId = fichierId;
    }

}
