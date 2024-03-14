package com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signUp;

public record SignUpReq(String name, String email, String password, String confirmPassword) {
}
