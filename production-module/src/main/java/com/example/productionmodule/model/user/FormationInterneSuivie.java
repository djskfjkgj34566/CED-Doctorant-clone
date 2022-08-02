package com.example.productionmodule.model.user;

import com.example.productionmodule.model.FormationInterneAdmin;
import com.example.productionmodule.model.Production;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationInterneSuivie extends Production {
    private Long formationInterneAdminId;
    @Transient
    private FormationInterneAdmin formationInterneAdmin;

    public FormationInterneSuivie(Long id, Long userId, Long fichierId, Long formationInterneAdminId) {
        super(id, userId, fichierId);
        this.formationInterneAdminId = formationInterneAdminId;
    }
}
