package com.jobsearchmanager.jobsearchmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Updated date is required")
    private String date;

    @Valid
    private DiscussionEnum type;

    private String description;

    private Date createdAt;

    @Valid
    @ManyToOne(targetEntity = Application.class)
    private Application relatedApplication;
}
