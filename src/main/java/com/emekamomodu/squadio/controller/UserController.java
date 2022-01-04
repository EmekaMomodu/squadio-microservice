package com.emekamomodu.squadio.controller;

import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private IUserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value="/{username}")
    public ResponseEntity<Response> getUserInformation(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserInformation(username));
    }

}
