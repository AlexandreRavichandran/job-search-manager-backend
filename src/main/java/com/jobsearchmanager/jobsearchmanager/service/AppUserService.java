package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;

import javax.persistence.NoResultException;

public interface AppUserService {

    AppUser edit(AppUser appUserToEdit) throws NoResultException;
}
