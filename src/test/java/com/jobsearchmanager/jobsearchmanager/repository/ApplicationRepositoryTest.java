package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApplicationRepositoryTest {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    void shouldReturnByRelatedUserId() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);

        this.appUserRepository.save(appUser);

        Application firstApplication = new Application();
        firstApplication.setId(2L);
        firstApplication.setArchived(Boolean.TRUE);
        firstApplication.setStatus(StatusEnum.RELAUNCHED);
        firstApplication.setRelatedUser(appUser);

        Application secondApplication = new Application();
        secondApplication.setId(3L);
        secondApplication.setArchived(Boolean.TRUE);
        secondApplication.setStatus(StatusEnum.RELAUNCHED);
        secondApplication.setRelatedUser(appUser);

        Application thirdApplication = new Application();
        thirdApplication.setId(4L);
        thirdApplication.setArchived(Boolean.FALSE);
        thirdApplication.setStatus(StatusEnum.HAVE_A_MEETING);
        thirdApplication.setRelatedUser(appUser);

        Application fourthApplication = new Application();
        fourthApplication.setId(5L);
        fourthApplication.setArchived(Boolean.FALSE);
        fourthApplication.setStatus(StatusEnum.HAVE_A_MEETING);
        fourthApplication.setRelatedUser(appUser);

        Collection<Application> applications = Arrays.asList(firstApplication,secondApplication,thirdApplication,fourthApplication);

        this.applicationRepository.saveAll(applications);

        Collection<Application> relatedApplication = this.applicationRepository.findByRelatedUserId(appUser.getId());

        assertTrue(relatedApplication.containsAll(applications));
    }

    @Test
    void shouldReturnByRelatedUserIdAndStatusAndArchived() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);

        this.appUserRepository.save(appUser);

        Application firstApplication = new Application();
        firstApplication.setId(2L);
        firstApplication.setArchived(Boolean.TRUE);
        firstApplication.setStatus(StatusEnum.RELAUNCHED);
        firstApplication.setRelatedUser(appUser);

        Application secondApplication = new Application();
        secondApplication.setId(3L);
        secondApplication.setArchived(Boolean.TRUE);
        secondApplication.setStatus(StatusEnum.RELAUNCHED);
        secondApplication.setRelatedUser(appUser);

        Application thirdApplication = new Application();
        thirdApplication.setId(4L);
        thirdApplication.setArchived(Boolean.FALSE);
        thirdApplication.setStatus(StatusEnum.HAVE_A_MEETING);
        thirdApplication.setRelatedUser(appUser);

        Application fourthApplication = new Application();
        fourthApplication.setId(5L);
        fourthApplication.setArchived(Boolean.FALSE);
        fourthApplication.setStatus(StatusEnum.HAVE_A_MEETING);
        fourthApplication.setRelatedUser(appUser);

        this.applicationRepository.saveAll(Arrays.asList(firstApplication,secondApplication,thirdApplication,fourthApplication));

        Collection<Application> archivedRelaunchedApplications = Arrays.asList(firstApplication,secondApplication);

        Collection<Application> getByArchivedAndRelaunched = this.applicationRepository.findByRelatedUserIdAndStatusAndArchived(appUser.getId(),StatusEnum.RELAUNCHED,Boolean.TRUE);

        assertTrue(getByArchivedAndRelaunched.containsAll(archivedRelaunchedApplications));
    }
}