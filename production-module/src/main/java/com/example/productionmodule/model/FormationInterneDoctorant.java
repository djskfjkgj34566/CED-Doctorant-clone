package com.example.productionmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationInterneDoctorant extends Production {

    private Long formationInterneAdminId;
    private String statusFormation;
    @Transient
    private FormationInterneAdmin formationInterneAdmin;

    public FormationInterneDoctorant(Long id, Long userId, Long fichierId, Long formationInterneAdminId,String statusFormation) {
        super(id, userId, fichierId);
        this.formationInterneAdminId = formationInterneAdminId;
        this.statusFormation= statusFormation;
    }
}
