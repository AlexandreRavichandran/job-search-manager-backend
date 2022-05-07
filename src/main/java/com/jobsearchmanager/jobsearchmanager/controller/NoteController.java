package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.Note;
import com.jobsearchmanager.jobsearchmanager.dto.NoteDTO;
import com.jobsearchmanager.jobsearchmanager.service.NoteServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications/{applicationId}/notes")
public class NoteController {

    @Autowired
    NoteServiceImpl noteService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Collection<NoteDTO>> browseByApplication(@PathVariable("applicationId") Long applicationId) {
        return new ResponseEntity<>(this.noteService.browseByApplication(applicationId)
                .stream()
                .map(note -> this.modelMapper.map(note, NoteDTO.class))
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDTO> read(@PathVariable("noteId") Long noteId) {
        return new ResponseEntity<>(this.modelMapper.map(this.noteService.read(noteId), NoteDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> edit(@RequestBody NoteDTO noteDTO) {
        Note note = this.modelMapper.map(noteDTO, Note.class);
        return new ResponseEntity<>(this.modelMapper.map(this.noteService.edit(note), NoteDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> add(@RequestBody NoteDTO noteDTO) {
        Note note = this.modelMapper.map(noteDTO, Note.class);

        return new ResponseEntity<>(this.modelMapper.map(this.noteService.add(note), NoteDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<NoteDTO> delete(@PathVariable("noteId") Long noteId ){

        return new ResponseEntity<>(this.modelMapper.map(this.noteService.delete(noteId), NoteDTO.class), HttpStatus.OK);
    }

}
