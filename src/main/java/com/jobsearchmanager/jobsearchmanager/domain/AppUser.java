package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "[a-zA-Z]+", message = "Your first name must be only letters.")
    private String firstName;
    @Pattern(regexp = "[a-zA-Z]+", message = "Your last name must be only letters.")
    private String lastName;

    @NotBlank(message = "An username is required.")
    private String username;
    @Email(message = "The email is required.")
    private String email;
    @NotBlank(message = "A password must be set.")
    private String password;

    @Valid
    @OneToMany(targetEntity = Application.class,mappedBy = "relatedUser")
    private Collection<Application> applications;

    @Valid
    @OneToMany(targetEntity = Activity.class, mappedBy = "relatedUser")
    private Collection<Activity> activities;
}
