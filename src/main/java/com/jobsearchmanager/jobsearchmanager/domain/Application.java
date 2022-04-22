package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String link;

    private Boolean archived;

    private ResultEnum result;

    private Date createdAt;

    private String companyName;

    private String companyAddress;

    private String contactEmail;

    private String contactPhoneNumber;

    private Boolean moved;

    @OneToMany(targetEntity = Activity.class,mappedBy = "relatedApplication")
    private Collection<Activity> activities;

    @OneToMany(targetEntity = Note.class,mappedBy = "relatedApplication")
    private Collection<Note> notes;

    @OneToMany(targetEntity = Discussion.class,mappedBy = "relatedApplication")
    private Collection<Discussion> discussions;

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser relatedUser;

}
