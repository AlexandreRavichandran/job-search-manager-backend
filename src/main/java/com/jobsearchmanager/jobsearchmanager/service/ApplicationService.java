package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Application;

import javax.persistence.NoResultException;
import java.util.Collection;

public interface ApplicationService {

    Collection<Application> browseByUser(Long userId) throws  NoResultException;

    Application read(Long applicationId) throws NoResultException;

    Application edit(Application editedApplication) throws NoResultException;

    Application add(Application applicationToAdd) throws NoResultException;

    Application delete(Long applicationId) throws NoResultException;

    Collection<Application> browseByStatus(Long userId, String status) throws NoResultException;
}
