package com.example.inventariobp.data.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String user;
    private String password;
}
