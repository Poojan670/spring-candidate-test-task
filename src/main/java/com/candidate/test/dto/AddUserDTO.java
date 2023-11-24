package com.candidate.test.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddUserDTO {
    @NotNull(message = "username is required")
    @NotBlank(message = "username can't be blank")
    private String username;
    @NotNull(message = "password is required")
    @NotBlank(message = "password can't be blank")
    private String password;
}
