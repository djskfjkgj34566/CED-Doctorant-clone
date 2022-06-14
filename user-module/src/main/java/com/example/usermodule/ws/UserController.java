package com.example.usermodule.ws;

import com.example.usermodule.model.User;
import com.example.usermodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/reset/password")
    public int sendPageUpdatePassword(@RequestBody String email) throws NoSuchAlgorithmException {
        return userService.sendPageUpdatePassword(email);
    }
}
