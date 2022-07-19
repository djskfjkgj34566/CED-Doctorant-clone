package com.example.usermodule.ws;

import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.ResponsableCED;
import com.example.usermodule.model.ResponsableFD;
import com.example.usermodule.service.RCEDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/v1/ms-user/responsableced")
public class RCEDController {

    @Autowired
    private RCEDService rcedService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ResponsableCED rced) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(rcedService.save(rced), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<ResponsableCED> findAll() {
        return rcedService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponsableCED findById(@PathVariable Long id) {
        return rcedService.findById(id).get();
    }

    @GetMapping("/vkey/{vkey}")
    public ResponsableCED getByVkey(@PathVariable String vkey) {
        return rcedService.getByVkey(vkey);
    }

    @PostMapping("/set/password")
    public int setRCEDPassword(@RequestBody PasswordDto passwordDto) {
        return rcedService.setRCEDPassword(passwordDto);
    }

}
