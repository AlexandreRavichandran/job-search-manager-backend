package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import com.jobsearchmanager.jobsearchmanager.repository.AppUserRepository;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.ThirdpartyAPISelector;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    AppUserRepository appUserRepository;

    @Mock
    ThirdpartyAPISelector apiSelector;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Test
    void whenGivenUserIdShouldReturnListOfApplications() {
        Application firstApplicationExample = new Application();
        firstApplicationExample.setId(1L);
        firstApplicationExample.setTitle("Title example 1");
        firstApplicationExample.setDescription("Description example 1");

        Application secondApplicationExample = new Application();
        secondApplicationExample.setId(2L);
        secondApplicationExample.setTitle("Title example 2");
        secondApplicationExample.setDescription("Description example 2");

        AppUser userExample = new AppUser();
        userExample.setId(1L);

        Collection<Application> applicationList = new ArrayList<>();
        applicationList.add(firstApplicationExample);
        applicationList.add(secondApplicationExample);

        Mockito.when(this.applicationRepository.findByRelatedUserId(1L)).thenReturn(applicationList);
        Mockito.when(this.appUserRepository.findById(1L)).thenReturn(Optional.of(userExample));
        this.applicationService.browseByUser(1L);

        Mockito.verify(this.applicationRepository).findByRelatedUserId(1L);
    }

    @Test
    void whenGivenStatusAndArchivedShouldReturnListOfApplications() {

        Application firstApplicationExample = new Application();
        firstApplicationExample.setId(1L);
        firstApplicationExample.setTitle("Title example 1");
        firstApplicationExample.setDescription("Description example 1");

        Application secondApplicationExample = new Application();
        secondApplicationExample.setId(2L);
        secondApplicationExample.setTitle("Title example 2");
        secondApplicationExample.setDescription("Description example 2");

        AppUser userExample = new AppUser();
        userExample.setId(1L);

        Collection<Application> applicationList = new ArrayList<>();
        applicationList.add(firstApplicationExample);
        applicationList.add(secondApplicationExample);

        Mockito.when(this.appUserRepository.findById(1L)).thenReturn(Optional.of(userExample));
        Mockito.when(this.applicationRepository.findByRelatedUserIdAndStatusAndArchived(1L, StatusEnum.APPLIED, Boolean.FALSE)).thenReturn(applicationList);

        this.applicationService.browseByStatusAndArchived(1L, StatusEnum.APPLIED, "false ");

        Mockito.verify(this.applicationRepository).findByRelatedUserIdAndStatusAndArchived(1L, StatusEnum.APPLIED, Boolean.getBoolean("false"));


    }

    @Test
    void whenGivenLinkShouldReturnApplication() throws IOException {
        Application applicationExample = new Application();
        applicationExample.setId(1L);
        applicationExample.setTitle("Imported application");
        String link = "http://www.fake-job-link";

        Mockito.when(this.apiSelector.guessServiceByLink(link)).thenReturn(applicationExample);

        this.applicationService.importByLink(link);

        Mockito.verify(this.apiSelector).guessServiceByLink(link);

    }

    @Test
    void whenGivenIdShouldReturnApplication() {
        Application applicationExample = new Application();
        applicationExample.setId(1L);
        applicationExample.setTitle("Imported application");

        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.of(applicationExample));

        this.applicationService.read(applicationExample.getId());

        Mockito.verify(this.applicationRepository).findById(1L);

    }

    @Test
    void whenGivenEditedApplicationShouldReturnEditedApplication() {
        Application basicApplicationExample = new Application();
        basicApplicationExample.setId(1L);
        basicApplicationExample.setTitle("Title example 1");
        basicApplicationExample.setDescription("Description example 1");

        Application editedApplication = new Application();
        editedApplication.setId(1L);
        editedApplication.setTitle("Title example 2");
        editedApplication.setDescription("Description example 2");

        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.of(basicApplicationExample));
        Mockito.when(this.applicationRepository.save(basicApplicationExample)).thenReturn(editedApplication);

        Application testEditedApplication = this.applicationService.edit(basicApplicationExample);

        Mockito.verify(this.applicationRepository).save(basicApplicationExample);
        assertEquals("Title example 2", testEditedApplication.getTitle());
    }

    @Test
    void whenGivenNewApplicationShouldReturnNewApplication() {
        Application newApplicationExample = new Application();
        newApplicationExample.setId(1L);
        newApplicationExample.setTitle("Title example 1");
        newApplicationExample.setDescription("Description example 1");

        Mockito.when(this.applicationRepository.save(newApplicationExample)).thenReturn(newApplicationExample);

        Application testNewApplication = this.applicationService.add(newApplicationExample);
        Mockito.verify(this.applicationRepository).save(newApplicationExample);
        assertEquals(newApplicationExample.getTitle(), testNewApplication.getTitle());
    }

    @Test
    void whenGivenIdShouldReturnDeleteApplication() {
        Application applicationExample = new Application();
        applicationExample.setId(1L);
        applicationExample.setTitle("Title example 1");
        applicationExample.setDescription("Description example 1");
        Mockito.when(this.applicationRepository.findById(applicationExample.getId())).thenReturn(Optional.of(applicationExample));
        this.applicationService.delete(applicationExample.getId());

        Mockito.verify(this.applicationRepository).delete(applicationExample);
    }

    @Test
    void whenGivenWrongIdShouldThrowNoResultException() {
        Application applicationExample = new Application();
        applicationExample.setId(1L);
        applicationExample.setTitle("Title example 1");
        applicationExample.setDescription("Description example 1");
        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoResultException.class, () -> {
            this.applicationService.read(1L);
        });
        assertThrows(NoResultException.class, () -> {
            this.applicationService.edit(applicationExample);
        });
        assertThrows(NoResultException.class, () -> {
            this.applicationService.delete(1L);
        });
    }
}