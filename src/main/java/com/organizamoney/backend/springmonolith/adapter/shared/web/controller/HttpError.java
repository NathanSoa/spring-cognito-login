package com.organizamoney.backend.springmonolith.adapter.shared.web.controller;

import lombok.Data;

@Data
public class HttpError {

    private String message;
    private String route;
    private Integer code;
}
