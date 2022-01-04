package com.emekamomodu.squadio.controller;

import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 8:58 AM
 */
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping(value="/{user-id}")
    public ResponseEntity<Response> getAllUserAccounts(@PathVariable(name = "user-id") String userId) {
        return ResponseEntity.ok(accountService.getAllUserAccounts(userId));
    }

}
