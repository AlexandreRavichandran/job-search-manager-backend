package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
