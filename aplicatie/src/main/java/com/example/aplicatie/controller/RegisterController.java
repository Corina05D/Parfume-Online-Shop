package com.example.aplicatie.controller;

import com.example.aplicatie.controller.responses.ErrorResponse;
import com.example.aplicatie.dto.RegisterDTO;
import com.example.aplicatie.model.User;
import com.example.aplicatie.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserServiceImpl userService;

    @Autowired
    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity saveNewUser(@RequestBody RegisterDTO registerDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser2(registerDTO));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }

    }
}
