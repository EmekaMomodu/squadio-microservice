package com.emekamomodu.squadio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 3:36 PM
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return (ResponseEntity<?>) ResponseEntity.ok("Hello World");
    }
}
