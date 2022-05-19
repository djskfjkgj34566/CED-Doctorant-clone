package com.example.productionmodule.repository;

import com.example.productionmodule.model.Communication;
import com.example.productionmodule.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunicationRepo extends JpaRepository<Communication, Long> {

    public Communication findOneByUserIdAndId(Long userId, Long id);
    public List<Communication> findAllByUserId(Long userId);
    @Query("select count(c) from Communication c")
    public Long countNumberOfData();

}
