package com.example.usermodule.ws;

import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.ResponsableFD;
import com.example.usermodule.service.RFDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ms-user/responsablefd")
public class RFDController {

    @Autowired
    private RFDService rfdService;

    @PostMapping("/")
    public ResponseEntity<ResponsableFD> save(@RequestBody ResponsableFD rfd) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(rfdService.save(rfd), HttpStatus.OK);
    }

    @GetMapping("/vkey/{vkey}")
    public ResponsableFD getByVkey(@PathVariable String vkey) {
        return rfdService.getByVkey(vkey);
    }

    @PostMapping("/set/password")
    public int setRFDPassword(@RequestBody PasswordDto passwordDto) {
        return rfdService.setRFDPassword(passwordDto);
    }


    @GetMapping("/")
    public List<ResponsableFD> findAll() {
        return rfdService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponsableFD findById(@PathVariable Long id) {
        return rfdService.findById(id).get();
    }
}
