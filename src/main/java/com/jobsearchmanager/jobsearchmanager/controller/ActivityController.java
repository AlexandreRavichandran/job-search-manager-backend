package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import com.jobsearchmanager.jobsearchmanager.dto.ActivityDTO;
import com.jobsearchmanager.jobsearchmanager.service.ActivityServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications/{applicationId}/activities")
public class ActivityController {

    @Autowired
    ActivityServiceImpl activityService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<ActivityDTO>> getByApplicationId(@PathVariable("applicationId") Long applicationId){
        return new ResponseEntity<>(this.activityService.browseByApplication(applicationId)
                .stream()
                .map(activity -> this.modelMapper.map(activity,ActivityDTO.class))
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityDTO> read(@PathVariable("activityId") Long activityId){
        return new ResponseEntity<>(
                this.modelMapper.map(this.activityService.read(activityId),ActivityDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ActivityDTO> add(@RequestBody ActivityDTO activityDTO){
        Activity activity = this.modelMapper.map(activityDTO, Activity.class);

        return new ResponseEntity<>(
                this.modelMapper.map(this.activityService.add(activity),ActivityDTO.class),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<ActivityDTO> delete(@PathVariable("activityId") Long activityId){
        return new ResponseEntity<>(
                this.modelMapper.map(this.activityService.delete(activityId),ActivityDTO.class),
                HttpStatus.ACCEPTED
        );
    }
}
