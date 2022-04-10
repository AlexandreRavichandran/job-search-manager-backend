package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Collection<Application> findByUserId(Long userId);

    Collection<Application> findByUserIdAndStatus(Long userId, String status);
}
