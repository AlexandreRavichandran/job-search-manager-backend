package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    void shouldReturnByApplicationId() {
        Application application = new Application();
        application.setId(1L);

        this.applicationRepository.save(application);

        Note firstNote = new Note();
        firstNote.setId(2L);
        firstNote.setRelatedApplication(application);

        Note secondNote = new Note();
        secondNote.setId(3L);
        secondNote.setRelatedApplication(application);

        Collection<Note> notes = Arrays.asList(firstNote,secondNote);

        this.noteRepository.saveAll(notes);

        Collection<Note> getByApplication = this.noteRepository.findByRelatedApplicationId(application.getId());

        assertTrue(getByApplication.containsAll(notes));
    }
}