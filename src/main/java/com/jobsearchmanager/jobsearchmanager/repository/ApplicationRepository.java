package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Collection<Application> findByRelatedUserId(Long userId);

    Collection<Application> findByRelatedUserIdAndStatusAndArchived(
            Long userId,
            StatusEnum statusEnum,
            Boolean archived
    );

}
