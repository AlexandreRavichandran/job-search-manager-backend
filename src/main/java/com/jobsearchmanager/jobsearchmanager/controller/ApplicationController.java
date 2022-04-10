package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.dto.ApplicationDTO;
import com.jobsearchmanager.jobsearchmanager.service.ApplicationServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    ApplicationServiceImpl applicationService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<ApplicationDTO>> getByStatus(@RequestParam("status") String status) {

        return new ResponseEntity<>(
                this.applicationService.browseByStatus(1L, status)
                        .stream()
                        .map(application -> this.modelMapper.map(application, ApplicationDTO.class))
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> read(@PathVariable("application") Long applicationId) {

        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.read(applicationId),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> edit(@RequestBody ApplicationDTO applicationDTO){
        Application application = this.modelMapper.map(applicationDTO, Application.class);

        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.edit(application),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> add(@RequestBody ApplicationDTO applicationDTO){
        Application application = this.modelMapper.map(applicationDTO, Application.class);

        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.add(application),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> delete(@PathVariable Long applicationId){

        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.delete(applicationId),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }


}
