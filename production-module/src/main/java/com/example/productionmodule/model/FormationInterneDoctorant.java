package com.example.productionmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationInterneDoctorant extends Production {

    private Long formationInterneAdminId;

    public FormationInterneDoctorant(Long id, Long userId, Long fichierId, Long formationInterneAdminId) {
        super(id, userId, fichierId);
        this.formationInterneAdminId = formationInterneAdminId;
    }
}
