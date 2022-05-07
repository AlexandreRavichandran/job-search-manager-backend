package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.service.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractController {

    @Autowired
    private AppUserServiceImpl appUserService;

    protected AppUser getCurrentLoggedUser(){

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        return this.appUserService.getAppUserByUsername(username);

    }

}
