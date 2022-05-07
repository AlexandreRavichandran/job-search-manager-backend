package com.jobsearchmanager.jobsearchmanager.dto;

import com.jobsearchmanager.jobsearchmanager.domain.DiscussionEnum;
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
