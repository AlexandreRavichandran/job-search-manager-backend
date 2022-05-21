package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Collection;

public interface ApplicationService {

    Collection<Application> browseByUser(Long userId) throws  NoResultException;

    Application read(Long applicationId) throws NoResultException;

    Application edit(Application editedApplication) throws NoResultException;

    Application add(Application applicationToAdd);

    Application delete(Long applicationId) throws NoResultException;

    Collection<Application> browseByStatusAndArchived(Long userId, StatusEnum status, Boolean archived) throws NoResultException;

    Application importByLink(String link) throws NoResultException, IOException;
}
