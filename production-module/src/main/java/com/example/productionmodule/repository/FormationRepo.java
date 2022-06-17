package com.example.productionmodule.repository;

import com.example.productionmodule.model.Communication;
import com.example.productionmodule.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {

    public Formation findOneByUserIdAndId(Long userId, Long id);
    public List<Formation> findAllByUserId(Long userId);
    @Query("select count(c) from Formation c")
    public Long countNumberOfData();

    Long countNumberOfDataByUserId(Long userId);

}
