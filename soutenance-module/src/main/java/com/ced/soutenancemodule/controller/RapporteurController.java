package com.ced.soutenancemodule.controller;

import com.ced.soutenancemodule.model.EmailMessage;
import com.ced.soutenancemodule.model.Rapporteur;
import com.ced.soutenancemodule.service.EmailSenderService;
import com.ced.soutenancemodule.service.RapporteurService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/ms-soutenance")
public class RapporteurController {

    @Autowired
    RapporteurService service;
    @Autowired
    private EmailSenderService emailSenderService;


    @PostMapping("/")
    public int save(@RequestBody Rapporteur rapporteur){
        return service.save(rapporteur);
    }

    @GetMapping("/doctorantId/{id}")
    public List<Rapporteur> findByDoctorantId(@PathVariable Long id) {
        return service.findByDoctorantId(id);
    }

    @GetMapping("/count/rapporteur/{doctorantId}")
    public int countByDoctorantId(@PathVariable Long doctorantId) {
        return service.countByDoctorantId(doctorantId);
    }

    @GetMapping("/export/rapporteurs/{doctorantId}")
    public ResponseEntity<byte[]> exportRapporteursDoc(@PathVariable Long doctorantId) throws JRException, IOException {
        return service.exportReport(doctorantId);
    }


    @PostMapping("/send-mail")
    public String sendM(@RequestBody String to, String subject, String text){
        /*EmailMessage emailMessage1 =  emailSenderService.EnvoieMessage(email);*/
        //emailSenderService.sendSimpleMessage(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        //emailSenderService.sendEMail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        emailSenderService.sendEMail(to,subject,text);
        return "Message envoyé";
    }
}
