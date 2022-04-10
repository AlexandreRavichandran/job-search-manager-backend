package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Collection<Activity> findByApplicationId(Long applicationId);
}
