package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Collection<Note> findByApplicationId(Long applicationId);

}
