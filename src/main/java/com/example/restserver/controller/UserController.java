package com.example.restserver.controller;


import com.example.restserver.entity.UserEntity;
import com.example.restserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try{
            userService.registration(user);
            return ResponseEntity.ok().body("Пользоваьтель создан");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Невозможно создать такого пользователя");
        }
    }
}
