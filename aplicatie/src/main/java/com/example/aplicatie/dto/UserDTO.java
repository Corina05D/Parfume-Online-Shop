package com.example.aplicatie.dto;

import javax.validation.constraints.Pattern;

public class UserDTO {
    @Pattern(regexp = "[a-zA-Z ]+")
    public String name;

    @Pattern(regexp = ".{8,}")
    public String password;
}
