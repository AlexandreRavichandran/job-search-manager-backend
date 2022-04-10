package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
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

}
