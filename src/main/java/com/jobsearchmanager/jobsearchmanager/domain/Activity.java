package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private StatusEnum status;

    private ResultEnum result;

    private Date changedAt;

    @ManyToOne(targetEntity = Application.class)
    private Application relatedApplication;
}
