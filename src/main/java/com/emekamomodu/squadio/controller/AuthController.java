package com.emekamomodu.squadio.controller;

import com.emekamomodu.squadio.model.request.AuthRequest;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 7:26 PM
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}
