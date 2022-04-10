package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private StatusEnum status;

    private String link;

    private Boolean archived;

    private ResultEnum result;

    private Date createdAt;

    private String companyName;

    private String companyAddress;

    private String contactEmail;

    private String contactPhoneNumber;

    @OneToMany(targetEntity = Activity.class,mappedBy = "relatedApplication")
    private Collection<Activity> activities;

    @OneToMany(targetEntity = Note.class,mappedBy = "relatedApplication")
    private Collection<Note> notes;

    @OneToMany(targetEntity = Discussion.class,mappedBy = "relatedApplication")
    private Collection<Discussion> discussions;

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser relatedUser;

}
