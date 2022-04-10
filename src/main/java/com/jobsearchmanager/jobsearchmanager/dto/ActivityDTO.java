package com.jobsearchmanager.jobsearchmanager.dto;

import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {
    private Long id;

    private StatusEnum status;

    private ResultEnum result;

    private Date changedAt;
}
