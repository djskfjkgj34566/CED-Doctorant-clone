package com.example.usermodule.ws;

import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.Doctorant;
import com.example.usermodule.model.User;
import com.example.usermodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/v1/ms-user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/reset/password/{email}")
    public int sendPageUpdatePassword(@PathVariable String email) throws NoSuchAlgorithmException {
        return userService.sendPageUpdatePassword(email);
    }

    @PostMapping(value = "/update/passwd", consumes = {"application/json"})
    public int updatePassword(@RequestBody PasswordDto passwordDto){
        return userService.updateUserPassword(passwordDto);
    }

    @GetMapping("/vkey/{vkey}")
    public ResponseEntity<User> getByVkey(@PathVariable String vkey){
        User user = userService.getByVkey(vkey);
        if(user!=null) return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
