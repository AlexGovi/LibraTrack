package com.lilbrary.library_mananagement.service;

import com.lilbrary.library_mananagement.JWT.JwtService;
import com.lilbrary.library_mananagement.dto.LoginRequestDTO;
import com.lilbrary.library_mananagement.dto.LoginResponseDTO;
import com.lilbrary.library_mananagement.dto.RegisterRequestDTO;
import com.lilbrary.library_mananagement.entity.User;
import com.lilbrary.library_mananagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already registered!!");
        }

        Set<String> roles=new HashSet<>();
        roles.add("ROLE_USER");

        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);

    }


    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already registered!!");
        }

        Set<String> roles=new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword())
        );

        User user=userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(()->new RuntimeException("User not found!!"));

        String token=jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();



    }


}
