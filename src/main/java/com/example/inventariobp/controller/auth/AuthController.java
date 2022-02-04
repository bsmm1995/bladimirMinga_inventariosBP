package com.example.inventariobp.controller.auth;

import com.example.inventariobp.data.auth.AuthenticationRequest;
import com.example.inventariobp.data.auth.AuthenticationResponse;
import com.example.inventariobp.service.auth.AuthService;
import com.example.inventariobp.utils.auth.JWTUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JWTUtil jwtUtil;

    @ApiOperation("Session start")
    @PostMapping(value = "login", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest data) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUser(), data.getPassword()));
            UserDetails userDetails = authService.loadUserByUsername(data.getUser());
            String jwt = jwtUtil.generateToken(userDetails);
            String refreshJwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt, refreshJwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
