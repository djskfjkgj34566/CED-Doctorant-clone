package com.example.productionmodule.repository;

import com.example.productionmodule.model.FormationInterneAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormationInterneAdminRepo extends JpaRepository<FormationInterneAdmin, Long> {

    @Query("select count(c) from FormationInterneAdmin c")
    public Long countNumberOfData();
}
