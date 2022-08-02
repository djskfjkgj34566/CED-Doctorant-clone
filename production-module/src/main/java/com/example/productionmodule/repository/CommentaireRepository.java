package com.example.productionmodule.repository;

import com.example.productionmodule.model.CommentPro;
import com.example.productionmodule.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    @Query("select c.text, c.date_comment from Commentaire c ")
    public List<Commentaire> FindAllComments();

    @Query("SELECT NEW com.example.productionmodule.model.CommentPro(p.id, p.titre, c.text,c.date_comment)\n" +
            "\tFROM Publication p, Commentaire c \n" +
            "\twhere p.id=c.productionId\n" +
            "\tAnd p.id= :id")
    public List<CommentPro> FindAllCommentsByForOnePub(@Param("id") Long id);

    @Query(value = "SELECT p.id, p.titre, c.text,c.date_comment\n" +
            "\tFROM Publication p, Commentaire c \n" +
            "\twhere p.id=c.productionId\n" +
            "\tAnd p.id= :id", nativeQuery = true)
    public List<Object[]> FindAllCommentsByIdPub(@Param("id") Long id);
}
