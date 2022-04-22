package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Collection<Activity> findByRelatedApplication(Long applicationId);

    @Query(value = "SELECT a from Activity a JOIN a.relatedUser u WHERE u.id = :userId")
    Collection<Activity> findByRelatedUser(Long userId);
}
