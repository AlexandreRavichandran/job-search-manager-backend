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
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Application title cannot be null.")
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Valid
    private StatusEnum status;

    @NotBlank(message = "Application link is required")
    private String link;

    private Boolean archived;

    @Valid
    private ResultEnum result;

    private Date createdAt;

    private String companyName;

    private String companyAddress;

    @Pattern(regexp = "[a-zA-Z]+", message = "Contact name can only contain letters")
    private String contactName;

    @Email
    private String contactEmail;

    @Pattern(regexp = "\\d+")
    private String contactPhoneNumber;

    private Boolean moved;

    @OneToMany(targetEntity = Activity.class,mappedBy = "relatedApplication",cascade = CascadeType.ALL)
    private Collection<Activity> activities;

    @OneToMany(targetEntity = Note.class,mappedBy = "relatedApplication",cascade = CascadeType.ALL)
    private Collection<Note> notes;

    @OneToMany(targetEntity = Discussion.class,mappedBy = "relatedApplication",cascade = CascadeType.ALL)
    private Collection<Discussion> discussions;

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser relatedUser;

}
