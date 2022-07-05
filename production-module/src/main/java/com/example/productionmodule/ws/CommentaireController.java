package com.example.productionmodule.ws;


import com.example.productionmodule.dto.CommentaireDto;
import com.example.productionmodule.model.CommentPro;
import com.example.productionmodule.model.Commentaire;
import com.example.productionmodule.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ms-production")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @PostMapping("/comments")
    public Commentaire save(@RequestBody CommentaireDto commentaireDto){
        return commentaireService.save(commentaireDto);
    }
    @GetMapping("/comments")
    public List<Commentaire> getCommentForOneProduction(@PathVariable Long userId, @PathVariable Long id){
        return commentaireService.getAllComments();
    }

    @GetMapping("/commentsss")
    public List<Commentaire> getComments(){
        return commentaireService.getAll();
    }
    @GetMapping("/commentss/{id}")
    public List<Object[]> getCommentForOneProd(@PathVariable Long id){
        return commentaireService.getAllCommentsByIdPubli(id);
    }
    /*
    @GetMapping("/commentss/{id}")
    public List<CommentPro> getCommentForOneProd(@PathVariable Long id){
        return commentaireService.getAllCommentsByIdPubli(id);
    }*/
    /*
    @GetMapping("/one/userId/{userId}/id/{id}")
    public List<Commentaire> getCommentForOneProduction(@PathVariable Long userId, @PathVariable Long id){
        return commentaireService.getAllComments();
    }*/

}
