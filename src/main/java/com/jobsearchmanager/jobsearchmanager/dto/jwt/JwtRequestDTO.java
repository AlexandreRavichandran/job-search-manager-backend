package com.jobsearchmanager.jobsearchmanager.dto.jwt;

import lombok.Data;

@Data
public class JwtRequestDTO {
    private String username;
    private String password;

}
