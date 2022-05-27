package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.dto.AppUserDTO;
import com.jobsearchmanager.jobsearchmanager.dto.jwt.JwtRequestDTO;
import com.jobsearchmanager.jobsearchmanager.dto.jwt.JwtResponseDTO;
import com.jobsearchmanager.jobsearchmanager.service.AppUserServiceImpl;
import com.jobsearchmanager.jobsearchmanager.utils.JWTManager;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO jwtRequestDTO) throws BadCredentialsException {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequestDTO.getUsername(),
                            jwtRequestDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(new JwtResponseDTO(this.generateTokenByUsername(jwtRequestDTO.getUsername())), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> register(@RequestBody AppUserDTO appUserDTO) {
        AppUser appUser = this.modelMapper.map(appUserDTO, AppUser.class);
        appUser = this.appUserService.save(appUser);


        return new ResponseEntity<>(new JwtResponseDTO(this.generateTokenByUsername(appUser.getUsername())), HttpStatus.CREATED);
    }

    private String generateTokenByUsername(String username) {
        final UserDetails userDetails = this.appUserService.loadUserByUsername(username);

        return this.jwtManager.generateToken(userDetails);
    }
}
