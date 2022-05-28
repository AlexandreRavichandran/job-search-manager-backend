package com.jobsearchmanager.jobsearchmanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {
    private Long id;

    private String status;

    private String result;

    private String description;

    private Date changedAt;
}
