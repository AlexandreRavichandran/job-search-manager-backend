package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.Discussion;
import com.jobsearchmanager.jobsearchmanager.dto.DiscussionDTO;
import com.jobsearchmanager.jobsearchmanager.service.DiscussionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications/{applicationId}/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionServiceImpl discussionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<DiscussionDTO>> browseByApplication(@PathVariable("applicationId") Long applicationId){

        return new ResponseEntity<>(this.discussionService.browseByApplication(applicationId)
                .stream()
                .map(discussion -> this.modelMapper.map(discussion,DiscussionDTO.class))
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<DiscussionDTO> read(@PathVariable("discussionId") Long discussionId){

        return new ResponseEntity<>(this.modelMapper.map(this.discussionService.read(discussionId),DiscussionDTO.class)
                ,HttpStatus.OK
        );
    }

    @PutMapping("/{discussionId}")
    public ResponseEntity<DiscussionDTO> edit(@RequestBody DiscussionDTO discussionDTO){
        Discussion discussion = this.modelMapper.map(discussionDTO,Discussion.class);
        return new ResponseEntity<>(this.modelMapper.map(this.discussionService.edit(discussion),
                DiscussionDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DiscussionDTO> add(@RequestBody DiscussionDTO discussionDTO){
        Discussion discussion = this.modelMapper.map(discussionDTO, Discussion.class);

        return new ResponseEntity<>(this.modelMapper.map(this.discussionService.add(discussion),
                DiscussionDTO.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{discussionId}")
    public ResponseEntity<DiscussionDTO> delete(@PathVariable("discussionId") Long discussionId){

        return new ResponseEntity<>(this.modelMapper.map(this.discussionService.delete(discussionId),
                DiscussionDTO.class),
                HttpStatus.OK
        );
    }

}
