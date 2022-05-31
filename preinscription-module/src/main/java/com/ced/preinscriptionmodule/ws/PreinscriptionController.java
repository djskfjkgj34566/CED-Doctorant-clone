package com.ced.preinscriptionmodule.ws;

import com.ced.preinscriptionmodule.model.Preinscription;
import com.ced.preinscriptionmodule.service.PreinscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/ms-preinscription")
public class PreinscriptionController {

    @Autowired
    private PreinscriptionService preinscriptionService;

    @GetMapping("/all")
    public List<Preinscription> getAll(){
        return preinscriptionService.getAll();
    }

    @GetMapping("/{cin}")
    public Preinscription getByCin(@PathVariable String cin){
        return preinscriptionService.getByCin(cin);
    }

    @PostMapping("/")
    public  int save(@RequestBody Preinscription preinscription){
        return preinscriptionService.save(preinscription);
    }


    @PutMapping("/")
    public int update(@RequestBody Preinscription preinscription){
        System.out.println("method update in preinscription ms rest called successfully");
        return preinscriptionService.update(preinscription);
    }

    @GetMapping("/get/count")
    public Long countData() {
        return preinscriptionService.countData();
    }
}
