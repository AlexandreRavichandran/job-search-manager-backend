package com.jobsearchmanager.jobsearchmanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoteDTO {

    private Long id;

    private Date date;

    private String description;

    private Date createdAt;

}
