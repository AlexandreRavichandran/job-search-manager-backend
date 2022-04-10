package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.repository.AppUserRepository;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Collection<Application> browseByUser(Long userId) throws NoResultException {
        AppUser appUser = this.appUserRepository.findById(userId).orElseThrow(NoResultException::new);

        return this.applicationRepository.findByRelatedUserId(appUser.getId());
    }

    @Override
    public Collection<Application> browseByStatus(Long userId) throws NoResultException {
        AppUser appUser = this.appUserRepository.findById(userId).orElseThrow(NoResultException::new);

        return this.applicationRepository.findByRelatedUserId(appUser.getId());
    }

    @Override
    public Application read(Long applicationId) throws NoResultException {

        return this.applicationRepository.findById(applicationId).orElseThrow(NoResultException::new);
    }

    @Override
    public Application edit(Application editedApplication) throws NoResultException {
        this.applicationRepository.findById(editedApplication.getId()).orElseThrow(NoResultException::new);

        return this.applicationRepository.save(editedApplication);
    }

    @Override
    public Application add(Application applicationToAdd) throws NoResultException {

        return this.applicationRepository.save(applicationToAdd);
    }

    @Override
    public Application delete(Long applicationId) throws NoResultException {
        Application application = this.applicationRepository.findById(applicationId).orElseThrow(NoResultException::new);
        this.applicationRepository.delete(application);

        return application;

    }
}
