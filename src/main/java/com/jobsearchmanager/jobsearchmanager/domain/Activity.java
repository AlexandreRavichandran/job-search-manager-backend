package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private StatusEnum status;

    private ResultEnum result;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private Date changedAt;

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser relatedUser;

    @ManyToOne(targetEntity = Application.class)
    private Application relatedApplication;
}
