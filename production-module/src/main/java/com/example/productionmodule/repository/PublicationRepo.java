package com.example.productionmodule.repository;

import com.example.productionmodule.model.Formation;
import com.example.productionmodule.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepo extends JpaRepository<Publication, Long> {


    public Publication findOneByUserIdAndId(Long userId, Long id);
    public List<Publication> findAllByUserId(Long userId);

    @Query("select count(c) from Publication c")
    public Long countNumberOfData();

    @Query("select count(c) from Publication c\n" +
            "where  c.userId=:userId")
    public int countNumberOfDataByUserId(@Param("userId") Long userId);

    /*@Query("SELECT p.id, p.titre, c.text,c.date_comment,u.nom\n" +
            "\tFROM Publication p, Commentaire c, User u \n" +
            "\twhere u.id=c.userId\n" +
            "\tAnd p.id=c.productionId\n" +
            "\tAnd p.id= :id")
    public  Publication findOneById(@Param("id") Long id);*/


    //reccuperer une publication avec la liste de commentaires
    /*@Query("SELECT p.id, p.titre, c.text,c.date_comment\n" +
            "\tFROM Publication p, Commentaire c \n" +
            "\twhere p.id=c.productionId\n" +
            "\tAnd p.id= :id")
    public  List<Publication> findOneById(@Param("id") Long id);*/
}
