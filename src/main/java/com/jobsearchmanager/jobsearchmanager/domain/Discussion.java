package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;

    private DiscussionEnum type;

    private String comment;

    private Date createdAt;

    @ManyToOne(targetEntity = Application.class)
    private Application relatedApplication;
}
