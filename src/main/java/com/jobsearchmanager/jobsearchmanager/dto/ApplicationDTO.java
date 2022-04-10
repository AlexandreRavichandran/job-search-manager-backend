package com.jobsearchmanager.jobsearchmanager.dto;

import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class ApplicationDTO {

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
