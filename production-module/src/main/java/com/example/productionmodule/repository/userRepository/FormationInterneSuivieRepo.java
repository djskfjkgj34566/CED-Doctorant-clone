package com.example.productionmodule.repository.userRepository;

import com.example.productionmodule.model.FormationInterneDoctorant;
import com.example.productionmodule.model.user.FormationInterneSuivie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface FormationInterneSuivieRepo extends JpaRepository<FormationInterneSuivie, Long> {
    List<FormationInterneSuivie> findByUserId(Long userId);
    @Query("select count(c) from FormationInterneSuivie c")
    public Long countNumberOfData();

    List<FormationInterneSuivie> findAllByUserId(Long userId);

}
