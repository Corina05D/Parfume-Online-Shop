package com.example.aplicatie.controller;


import com.example.aplicatie.controller.responses.ErrorResponse;
import com.example.aplicatie.model.User;
import com.example.aplicatie.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public ResponseEntity findUserByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByName(name));
    }

    @PostMapping("")
    public ResponseEntity saveNewUser(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }

    }
}
