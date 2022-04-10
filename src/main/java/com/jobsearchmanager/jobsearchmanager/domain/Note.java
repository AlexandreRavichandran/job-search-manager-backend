package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private String description;

    private Date createdAt;

    @ManyToOne(targetEntity = Application.class)
    private Application relatedApplication;
}
