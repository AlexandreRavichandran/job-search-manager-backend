package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.dto.jwt.JwtRequestDTO;
import com.jobsearchmanager.jobsearchmanager.dto.jwt.JwtResponseDTO;
import com.jobsearchmanager.jobsearchmanager.service.AppUserServiceImpl;
import com.jobsearchmanager.jobsearchmanager.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JWTManager jwtManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserServiceImpl appUserService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO jwtRequestDTO) throws BadCredentialsException
    {
        try{
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequestDTO.getUsername(),
                            jwtRequestDTO.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = this.appUserService.loadUserByUsername(jwtRequestDTO.getUsername());

        final String token = this.jwtManager.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponseDTO(token), HttpStatus.OK);
    }
}
