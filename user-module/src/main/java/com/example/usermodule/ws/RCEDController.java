package com.example.usermodule.ws;

import com.example.usermodule.model.ResponsableCED;
import com.example.usermodule.service.RCEDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ms-user/responsableced")
public class RCEDController {

    @Autowired
    private RCEDService rcedService;

    @GetMapping("/")
    public List<ResponsableCED> findAll() {
        return rcedService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponsableCED findById(@PathVariable Long id) {
        return rcedService.findById(id).get();
    }
}
