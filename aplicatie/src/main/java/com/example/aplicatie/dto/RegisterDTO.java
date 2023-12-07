package com.example.aplicatie.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Setter
@Getter
@AllArgsConstructor
public class RegisterDTO {
    String name;
    String email;
    String password;
}
