package com.jobsearchmanager.jobsearchmanager.dto;

import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
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
