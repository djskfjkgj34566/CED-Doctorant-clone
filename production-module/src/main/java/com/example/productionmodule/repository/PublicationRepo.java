package com.example.productionmodule.repository;

import com.example.productionmodule.model.Formation;
import com.example.productionmodule.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepo extends JpaRepository<Publication, Long> {


    public Publication findOneByUserIdAndId(Long userId, Long id);
    public List<Publication> findAllByUserId(Long userId);

    @Query("select count(c) from Publication c")
    public Long countNumberOfData();

}
