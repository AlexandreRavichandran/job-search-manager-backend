package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.repository.ActivityRepository;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Collection<Activity> browseByUserId(Long userId) {
        return this.activityRepository.findByRelatedUserId(userId);
    }

    @Override
    public Collection<Activity> browseByApplication(Long applicationId) throws NoResultException {
        Application application = this.applicationRepository.findById(applicationId).orElseThrow(NoResultException::new);

        return this.activityRepository.findByRelatedApplicationId(application.getId());
    }

    @Override
    public Activity read(Long activityId) throws NoResultException {

        return this.activityRepository.findById(activityId).orElseThrow(NoResultException::new);
    }

    @Override
    public Activity edit(Activity activityToEdit) throws NoResultException {

        this.activityRepository.findById(activityToEdit.getId()).orElseThrow(NoResultException::new);

        return this.activityRepository.save(activityToEdit);
    }

    @Override
    public Activity add(Activity activity) {
        return this.activityRepository.save(activity);
    }

    @Override
    public Activity delete(Long activityId) throws NoResultException {
        Activity activity = this.activityRepository.findById(activityId).orElseThrow(NoResultException::new);
        this.activityRepository.delete(activity);

        return activity;
    }
}
