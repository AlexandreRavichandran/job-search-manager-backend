package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;

import javax.persistence.NoResultException;
import java.util.Collection;

public interface ActivityService {

    Collection<Activity> browseByUserId(Long userId);

    Collection<Activity> browseByApplication(Long applicationId) throws NoResultException;

    Activity read(Long activityId) throws NoResultException;

    Activity edit(Activity activityToEdit) throws NoResultException;

    Activity add(Activity activity);

    Activity delete(Long activityId) throws NoResultException;

}
