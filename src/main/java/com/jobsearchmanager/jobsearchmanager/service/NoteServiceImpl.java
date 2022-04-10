package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.Note;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import com.jobsearchmanager.jobsearchmanager.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    NoteRepository noteRepository;

    @Override
    public Collection<Note> browseByApplication(Long applicationId) throws NoResultException {
        Application application = this.applicationRepository.findById(applicationId).orElseThrow(NoResultException::new);

        return this.noteRepository.findByApplicationId(application.getId());
    }

    @Override
    public Note read(Long noteId) throws NoResultException {

        return this.noteRepository.findById(noteId).orElseThrow(NoResultException::new);

    }

    @Override
    public Note edit(Note editedNote) throws NoResultException {
        this.noteRepository.findById(editedNote.getId()).orElseThrow(NoResultException::new);

        return this.noteRepository.save(editedNote);
    }

    @Override
    public Note add(Note noteToAdd) {

        return this.noteRepository.save(noteToAdd);
    }

    @Override
    public Note delete(Long noteId) throws NoResultException {
        Note note = this.noteRepository.findById(noteId).orElseThrow(NoResultException::new);
        this.noteRepository.delete(note);

        return note;
    }
}
