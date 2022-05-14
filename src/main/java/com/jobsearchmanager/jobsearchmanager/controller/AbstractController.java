package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import com.jobsearchmanager.jobsearchmanager.service.ActivityServiceImpl;
import com.jobsearchmanager.jobsearchmanager.service.AppUserServiceImpl;
import com.jobsearchmanager.jobsearchmanager.service.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public abstract class AbstractController {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private ActivityServiceImpl activityService;

    protected AppUser getCurrentLoggedUser(){

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        return this.appUserService.getAppUserByUsername(username);

    }

    protected Activity generateByAction(Long applicationId, String action, String data){
        Activity activity = new Activity();
        activity.setChangedAt(new Date());
        activity.setRelatedUser(this.getCurrentLoggedUser());
        activity.setRelatedApplication(this.applicationService.read(applicationId));

        switch (action){
            case "STATUS_UPDATE":
                activity.setStatus(StatusEnum.valueOf(data));
                break;
            case "RESULT_UPDATE":
                activity.setResult(ResultEnum.valueOf(data));
                break;
        }
        this.activityService.add(activity);
        return activity;
    }
}
