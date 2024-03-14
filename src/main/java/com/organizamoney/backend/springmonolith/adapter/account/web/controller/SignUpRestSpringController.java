package com.organizamoney.backend.springmonolith.adapter.account.web.controller;

import com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signUp.SignUpReq;
import com.organizamoney.backend.springmonolith.adapter.account.web.service.AccountWebServiceSpring;
import com.organizamoney.backend.springmonolith.adapter.shared.web.annotation.BaseRestSpringController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseRestSpringController("api/v1/accounts/sign-up")
public class SignUpRestSpringController {


    @Autowired
    private AccountWebServiceSpring accountService;

    @PostMapping()
    public ResponseEntity<?> signUp(@RequestBody SignUpReq request) {
        accountService.signup(request);
        return ResponseEntity.noContent().build();
    }
}
