package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Discussion;
import com.jobsearchmanager.jobsearchmanager.domain.Note;

import javax.persistence.NoResultException;
import java.util.Collection;

public interface NoteService {

    Collection<Note> browseByApplication(Long applicationId) throws NoResultException;

    Note read(Long noteId) throws NoResultException;

    Note edit(Note editedNote) throws NoResultException;

    Note add(Note noteToAdd);

    Note delete(Long noteId) throws NoResultException;

}
