package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Activity;
import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    void shouldReturnByApplicationId() {
        Application application = new Application();
        application.setId(1L);

        Activity firstActivity = new Activity();
        firstActivity.setId(1L);
        firstActivity.setRelatedApplication(application);

        Activity secondActivity = new Activity();
        secondActivity.setId(2L);
        secondActivity.setRelatedApplication(application);

        Collection<Activity> activities = Arrays.asList(firstActivity,secondActivity);

        Collection<Activity> relatedActivity = this.activityRepository.findByRelatedApplicationId(application.getId());

        assertThat(relatedActivity.containsAll(activities));
    }

    @Test
    void shouldReturnByUserId() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);

        Activity firstActivity = new Activity();
        firstActivity.setId(1L);
        firstActivity.setRelatedUser(appUser);

        Activity secondActivity = new Activity();
        secondActivity.setId(2L);
        secondActivity.setRelatedUser(appUser);

        Collection<Activity> activities = Arrays.asList(firstActivity,secondActivity);

        Collection<Activity> relatedActivity = this.activityRepository.findByRelatedUserId(appUser.getId());

        assertThat(relatedActivity.containsAll(activities));
    }
}