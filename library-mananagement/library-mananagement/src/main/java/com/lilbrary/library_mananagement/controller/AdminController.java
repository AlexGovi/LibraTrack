package com.lilbrary.library_mananagement.controller;

import com.lilbrary.library_mananagement.dto.RegisterRequestDTO;
import com.lilbrary.library_mananagement.entity.User;
import com.lilbrary.library_mananagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authService.registerAdminUser(registerRequestDTO));
    }

}
