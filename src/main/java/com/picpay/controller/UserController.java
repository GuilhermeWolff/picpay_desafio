package com.picpay.controller;

import com.picpay.dto.UserDTO;
import com.picpay.entity.User;
import com.picpay.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDTO userDto){
        log.info("Recebendo a entidade de User {}", userDto);
        User user = userService.createUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        log.info("Fim do processamento User{}", user);
        return ResponseEntity.created(uri).build();
    }

}