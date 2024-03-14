package com.organizamoney.backend.springmonolith.adapter.account.web.controller;

import com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signIn.SignInReq;
import com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signIn.SignInRes;
import com.organizamoney.backend.springmonolith.adapter.account.web.service.AccountWebServiceSpring;
import com.organizamoney.backend.springmonolith.adapter.shared.web.annotation.BaseRestSpringController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseRestSpringController("api/v1/accounts/sign-in")
public class SignInRestSpringController {

    @Autowired
    private AccountWebServiceSpring accountService;


    @PostMapping()
    public ResponseEntity<SignInRes> signIn(@RequestBody SignInReq request) {
        final var response = accountService.signIn(request);
        return ResponseEntity.ok(response);
    }
}
