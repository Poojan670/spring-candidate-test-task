package com.candidate.test.controller;

import com.candidate.test.entity.User;
import com.candidate.test.service.UserService;
import com.candidate.test.dto.AddUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Valid @RequestBody AddUserDTO addUserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(addUserDTO));
    }

}
