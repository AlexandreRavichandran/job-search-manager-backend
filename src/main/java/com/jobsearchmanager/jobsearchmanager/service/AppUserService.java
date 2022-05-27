package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.NoResultException;

public interface AppUserService extends UserDetailsService {

    AppUser edit(AppUser appUserToEdit) throws NoResultException;

    AppUser getAppUserByUsername(String username) throws NoResultException;

    AppUser save(AppUser appUser);
}
