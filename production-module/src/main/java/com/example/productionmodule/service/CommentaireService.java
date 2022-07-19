package com.example.productionmodule.service;

import com.example.productionmodule.dto.CommentaireDto;
import com.example.productionmodule.model.CommentPro;
import com.example.productionmodule.model.Commentaire;
import com.example.productionmodule.repository.CommentaireRepository;
import com.example.productionmodule.repository.CommentaireRepositoryy;
import com.example.productionmodule.repository.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepositoryy commentRepository;
    private CommentaireRepository commentProRepository;

    @Autowired
    private PublicationRepo publicationRepo;

    public Commentaire save(CommentaireDto commentaireDto){
           Commentaire cc =  new Commentaire(
                   commentaireDto.getText(),
                   commentaireDto.getUserId(),
                   commentaireDto.getProductionId()
            );
         /*commentaireDto.getText();  "28/06/2030 16:07:08"
        commentaireDto.getProductionId();
        commentaireDto.getUserId();
        System.out.println(commentaireDto.getText());*/
            /*cc.setCommentText(commentaire.getCommentText());
            cc.setUserId(commentaire.getUserId());
            cc.setProductionId(cc.getProductionId());*/

         commentRepository.save(cc);
        return cc;
    }

    public List<Commentaire> getAll(){
        return commentRepository.findAll();
    }

    public List<Commentaire> getAllComments(){
        return commentRepository.FindAllComments();
    }
/*
    public  List<CommentPro> getAllCommentsByIdPubli(Long id){

        return commentProRepository.FindAllCommentsByForOnePub(id);
    }*/

    public  List<Object[]> getAllCommentsByIdPubli(Long id){
        return  commentProRepository.FindAllCommentsByIdPub(id);
    }
}
