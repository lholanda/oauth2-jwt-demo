package com.devsuperior.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.demo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service; // posso fazer a injecao por constructor tambem

    @GetMapping
    public ResponseEntity<UserDetails> searchUserAndRolesByEmail(@RequestParam String userName) {

        // URI ?

        UserDetails user = service.loadUserByUsername(userName);

        return ResponseEntity.ok(user);
    }
    
    /*
    @GetMapping
    public ResponseEntity<UserLoginDTODetails> login(@RequestBody String password) {
        
    // URI ?

    UserDetails user = service.loadUserByUsername(userName);

    return ResponseEntity.ok(user);
    }
    */
}
