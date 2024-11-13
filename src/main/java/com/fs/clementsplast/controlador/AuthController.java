package com.fs.clementsplast.controlador;

import com.fs.clementsplast.dto.auth.AuthResponse;
import com.fs.clementsplast.dto.auth.LoginRequest;
import com.fs.clementsplast.dto.auth.RegisterRequest;
import com.fs.clementsplast.servicios.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clements-plast")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
