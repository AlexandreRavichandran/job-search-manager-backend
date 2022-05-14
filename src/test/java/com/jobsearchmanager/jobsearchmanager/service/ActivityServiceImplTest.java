package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.*;
import com.jobsearchmanager.jobsearchmanager.repository.ActivityRepository;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    ActivityRepository activityRepository;

    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ActivityServiceImpl activityService;

    @Test
    void whenGivenUserIdShouldReturnActivityList() {
        Activity firstActivityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Description example 1",
                new Date(),
                new AppUser(),
                new Application()
        );

        Activity secondActivityExample = new Activity(
                2L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Description example 2",
                new Date(),
                new AppUser(),
                new Application()
        );

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(firstActivityExample);
        activityList.add(secondActivityExample);

        Mockito.when(this.activityRepository.findByRelatedUser(1L)).thenReturn(activityList);

        this.activityService.browseByUserId(1L);

        Mockito.verify(this.activityRepository).findByRelatedUser(1L);
    }

    @Test
    void whenGivenApplicationIdShouldReturnActivityList() {
        Application application = new Application();
        application.setId(1L);

        Activity firstActivityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Description example 1",
                new Date(),
                new AppUser(),
                new Application()
        );

        Activity secondActivityExample = new Activity(
                2L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Description example 2",
                new Date(),
                new AppUser(),
                new Application()
        );

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(firstActivityExample);
        activityList.add(secondActivityExample);
        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        Mockito.when(this.activityRepository.findByRelatedApplication(1L)).thenReturn(activityList);

        this.activityService.browseByApplication(1L);

        Mockito.verify(this.activityRepository).findByRelatedApplication(1L);
    }

    @Test
    void whenGivenIdShouldReturnActivity() {
        Activity activityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Description example 1",
                new Date(),
                new AppUser(),
                new Application()
        );

        Mockito.when(this.activityRepository.findById(1L)).thenReturn(Optional.of(activityExample));

        this.activityService.read(1L);

        Mockito.verify(this.activityRepository).findById(1L);
    }

    @Test
    void whenGivenEditedActivityShouldReturnEditedActivity() {

        Activity basicActivityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Basic description",
                new Date(),
                new AppUser(),
                new Application()
        );

        Activity editedActivityExample = new Activity(
                2L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Edited description example 2",
                new Date(),
                new AppUser(),
                new Application()
        );

        Mockito.when(this.activityRepository.findById(1L)).thenReturn(Optional.of(basicActivityExample));
        Mockito.when(this.activityRepository.save(basicActivityExample)).thenReturn(editedActivityExample);
        Activity activity = this.activityService.edit(basicActivityExample);

        Mockito.verify(this.activityRepository).save(basicActivityExample);

        assertEquals(editedActivityExample.getDescription(), activity.getDescription());
    }

    @Test
    void whenGivenNewActivityShouldReturnCreatedActivity() {
        Activity newActivityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Basic description",
                new Date(),
                new AppUser(),
                new Application()
        );

        Mockito.when(this.activityRepository.save(newActivityExample)).thenReturn(newActivityExample);
        this.activityService.add(newActivityExample);
        Mockito.verify(this.activityRepository).save(newActivityExample);

    }

    @Test
    void whenGivenIdShouldReturnDeletedActivity() {
        Activity activityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Basic description",
                new Date(),
                new AppUser(),
                new Application()
        );
        Mockito.when(this.activityRepository.findById(activityExample.getId())).thenReturn(Optional.of(activityExample));
        this.activityService.delete(activityExample.getId());

        Mockito.verify(this.activityRepository).delete(activityExample);
    }

    @Test
    void whenGivenFalseIdShouldThrowNoResultException() {
        Mockito.when(this.activityRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> {
            this.activityService.browseByApplication(1L);
        });
        assertThrows(NoResultException.class, () -> {
            this.activityService.read(1L);
        });

        Activity basicActivityExample = new Activity(
                1L,
                StatusEnum.GOING_TO_APPLY,
                ResultEnum.NO_RESPONSE,
                "Basic description",
                new Date(),
                new AppUser(),
                new Application()
        );

        assertThrows(NoResultException.class, () -> {
            this.activityService.edit(basicActivityExample);
        });
        assertThrows(NoResultException.class, () -> {
            this.activityService.delete(1L);
        });
    }
}