package com.lilbrary.library_mananagement.controller;

import com.lilbrary.library_mananagement.dto.LoginRequestDTO;
import com.lilbrary.library_mananagement.dto.LoginResponseDTO;
import com.lilbrary.library_mananagement.dto.RegisterRequestDTO;
import com.lilbrary.library_mananagement.entity.User;
import com.lilbrary.library_mananagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){

        return ResponseEntity.ok(authService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
