package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Valid
    private StatusEnum status;

    @Valid
    private ResultEnum result;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @NotBlank(message = "Updated date is required.")
    private Date changedAt;

    @Valid
    @ManyToOne(targetEntity = AppUser.class)
    private AppUser relatedUser;

    @Valid
    @ManyToOne(targetEntity = Application.class)
    private Application relatedApplication;
}
