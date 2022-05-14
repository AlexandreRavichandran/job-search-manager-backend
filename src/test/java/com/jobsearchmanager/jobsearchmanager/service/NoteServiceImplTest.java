package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.*;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import com.jobsearchmanager.jobsearchmanager.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteServiceImpl noteService;

    @Test
    void whenGivenIdShouldReturnListOfNotes() {
        Application applicationExample = new Application();
        applicationExample.setId(2L);

        Note firstNoteExample = new Note();
        firstNoteExample.setId(1L);
        firstNoteExample.setDescription("Description example 1");

        Note secondNoteExample = new Note();
        secondNoteExample.setId(1L);
        secondNoteExample.setDescription("Description example 2");

        List<Note> noteList = new ArrayList<>();
        noteList.add(firstNoteExample);
        noteList.add(secondNoteExample);

        Mockito.when(this.applicationRepository.findById(2L)).thenReturn(Optional.of(applicationExample));
        Mockito.when(this.noteRepository.findByRelatedApplication(2L)).thenReturn(noteList);

        this.noteService.browseByApplication(2L);

        Mockito.verify(this.noteRepository).findByRelatedApplication(2L);
    }

    @Test
    void whenGivenIdShouldReturnNote() {
        Note noteExample = new Note();
        noteExample.setId(1L);
        noteExample.setDescription("Description example");

        Mockito.when(this.noteRepository.findById(1L)).thenReturn(Optional.of(noteExample));
        this.noteService.read(1L);

        Mockito.verify(this.noteRepository).findById(1L);

    }

    @Test
    void whenGivenEditedNoteShouldReturnEditedNote() {
        Note basicNoteExample = new Note();
        basicNoteExample.setId(1L);
        basicNoteExample.setDescription("Description example");

        Note editedNoteExample = new Note();
        editedNoteExample.setId(1L);
        editedNoteExample.setDescription("Edited description example");

        Mockito.when(this.noteRepository.findById(1L)).thenReturn(Optional.of(basicNoteExample));

        Mockito.when(this.noteRepository.save(editedNoteExample)).thenReturn(editedNoteExample);

        Note editedNoteTest = this.noteService.edit(editedNoteExample);

        Mockito.verify(this.noteRepository).save(editedNoteExample);
        assertEquals(editedNoteExample.getDescription(),editedNoteTest.getDescription());
    }

    @Test
    void whenGivenNewNoteShouldReturnNewNote() {
        Note newNoteExample = new Note();
        newNoteExample.setId(1L);
        newNoteExample.setDescription("Description example");

        Mockito.when(this.noteRepository.save(newNoteExample)).thenReturn(newNoteExample);

        this.noteService.add(newNoteExample);

        Mockito.verify(this.noteRepository).save(newNoteExample);
    }

    @Test
    void whenGivenIdShouldReturnDeletedNote() {
        Note noteExample = new Note();
        noteExample.setId(1L);
        noteExample.setDescription("Description example");

        Mockito.when(this.noteRepository.findById(1L)).thenReturn(Optional.of(noteExample));

        this.noteService.delete(noteExample.getId());

        Mockito.verify(this.noteRepository).delete(noteExample);
    }

    @Test
    void whenGivenFalseIdShouldThrowNoResultException() {
        Mockito.when(this.noteRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> {
            this.noteService.browseByApplication(1L);
        });
        assertThrows(NoResultException.class, () -> {
            this.noteService.read(1L);
        });

        Note noteExample = new Note();
        noteExample.setId(1L);

        assertThrows(NoResultException.class, () -> {
            this.noteService.edit(noteExample);
        });
        assertThrows(NoResultException.class, () -> {
            this.noteService.delete(1L);
        });
    }
}