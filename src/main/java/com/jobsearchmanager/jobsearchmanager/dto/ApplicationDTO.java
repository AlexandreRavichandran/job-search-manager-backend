package com.jobsearchmanager.jobsearchmanager.dto;

import com.jobsearchmanager.jobsearchmanager.domain.*;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Data
public class ApplicationDTO {

    private Long id;

    private String title;

    private String description;

    private String status;

    private String link;

    private Boolean archived;

    private String result;

    private Date createdAt;

    private String companyName;

    private String companyAddress;

    private String contactName;

    private String contactEmail;

    private String contactPhoneNumber;

    private Boolean moved;

    private Collection<ActivityDTO> activities;

    private Collection<NoteDTO> notes;

    private Collection<DiscussionDTO> discussions;

}
