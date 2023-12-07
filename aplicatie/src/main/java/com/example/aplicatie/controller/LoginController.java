package com.example.aplicatie.controller;


import com.example.aplicatie.controller.responses.ErrorResponse;
import com.example.aplicatie.dto.UserDTO;
import com.example.aplicatie.model.User;
import com.example.aplicatie.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO userDTO) {
        Optional<User> userOptional = userService.findFirstByNameAndPassword(userDTO);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            user.setLogat(true);
            userService.updateUser(user);
            return ResponseEntity.ok(userOptional.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("User not found!"));
        }
    }
}
