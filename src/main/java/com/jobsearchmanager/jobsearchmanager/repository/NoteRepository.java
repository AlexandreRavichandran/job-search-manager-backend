package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
