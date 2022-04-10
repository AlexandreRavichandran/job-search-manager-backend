package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;

    private DiscussionEnum type;

    private String comment;

    private Date createdAt;
}
