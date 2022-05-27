package com.jobsearchmanager.jobsearchmanager.dto;

import lombok.Data;

@Data
public class AppUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

}
