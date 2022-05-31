package com.example.chatmodule.ws;

import com.example.chatmodule.module.DemandeEncadrement;
import com.example.chatmodule.service.DemandeEncadrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ms-chat/encadrement")
public class DemandeEncadrementController {

    @Autowired
    private DemandeEncadrementService service;

    @PostMapping("/")
    public int save(@RequestBody DemandeEncadrement demande){
        return service.save(demande);
    }

    @GetMapping("/encadrantId/{id}")
    public DemandeEncadrement findByEncadrantId(@PathVariable Long id) {
        return service.findByEncadrantId(id);
    }
}
