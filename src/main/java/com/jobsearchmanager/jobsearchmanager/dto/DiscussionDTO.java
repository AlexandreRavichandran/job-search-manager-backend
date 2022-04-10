package com.jobsearchmanager.jobsearchmanager.dto;

import com.jobsearchmanager.jobsearchmanager.domain.DiscussionEnum;
import lombok.Data;

import java.util.Date;

@Data
public class DiscussionDTO {

    private Long id;

    private String date;

    private DiscussionEnum type;

    private String comment;

    private Date createdAt;
}
