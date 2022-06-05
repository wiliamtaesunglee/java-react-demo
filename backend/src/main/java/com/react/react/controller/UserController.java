package com.react.react.controller;

import com.react.react.model.Client;
import com.react.react.repository.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private ClientRepository clientRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ClientRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    @ResponseBody
    public void create(@RequestBody Client user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        clientRepository.save(user);
    }
}
