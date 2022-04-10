package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    Collection<Discussion> findByApplicationId(Long applicationId);

}
