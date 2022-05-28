package com.jobsearchmanager.jobsearchmanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiscussionDTO {

    private Long id;

    private String date;

    private String type;

    private String description;

    private Date createdAt;
}
