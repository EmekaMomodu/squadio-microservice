package com.emekamomodu.squadio.controller;

import com.emekamomodu.squadio.model.request.AccountStatementRequest;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.service.IAccountService;
import com.emekamomodu.squadio.service.IAccountStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IAccountStatementService accountStatementService;

    @GetMapping(value = "/{user-id}")
    public ResponseEntity<Response> getAllUserAccounts(@PathVariable(name = "user-id") String userId) {
        return ResponseEntity.ok(accountService.getAllUserAccounts(userId));
    }

    @PostMapping(value = "/statements")
    public ResponseEntity<Response> getAllAccountStatements(@RequestBody AccountStatementRequest accountStatementRequest) {
        return ResponseEntity.ok(accountStatementService.getAllAccountStatements(accountStatementRequest));
    }

}
