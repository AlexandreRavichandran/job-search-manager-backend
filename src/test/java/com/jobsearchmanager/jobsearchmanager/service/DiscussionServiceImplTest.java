package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.*;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import com.jobsearchmanager.jobsearchmanager.repository.DiscussionRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiscussionServiceImplTest extends MockitoExtension {

    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    DiscussionRepository discussionRepository;

    @InjectMocks
    DiscussionServiceImpl discussionService;

    @Test
    void whenGivenIdShouldReturnListOfDiscussion() {
        Application applicationExample = new Application();
        applicationExample.setId(2L);
        Discussion firstDiscussionExample = new Discussion();
        firstDiscussionExample.setId(1L);
        firstDiscussionExample.setDescription("Description example 1");

        Discussion secondDiscussionExample = new Discussion();
        secondDiscussionExample.setId(2L);
        secondDiscussionExample.setDescription("Description example 2");

        List<Discussion> discussionList = new ArrayList<>();
        discussionList.add(firstDiscussionExample);
        discussionList.add(secondDiscussionExample);

        Mockito.when(this.applicationRepository.findById(2L)).thenReturn(Optional.of(applicationExample));
        Mockito.when(this.discussionRepository.findByRelatedApplication(2L)).thenReturn(discussionList);

        this.discussionService.browseByApplication(2L);

        Mockito.verify(this.discussionRepository).findByRelatedApplication(2L);
    }

    @Test
    void whenGivenIdShouldReturnDiscussion() {
        Discussion firstDiscussionExample = new Discussion();
        firstDiscussionExample.setId(1L);
        firstDiscussionExample.setDescription("Description example");

        Mockito.when(this.discussionRepository.findById(1L)).thenReturn(Optional.of(firstDiscussionExample));

        this.discussionService.read(1L);

        Mockito.verify(this.discussionRepository).findById(1L);
    }

    @Test
    void whenGivenEditedDiscussionShouldEditedDiscussion() {
        Discussion basicDiscussionExample = new Discussion();
        basicDiscussionExample.setId(1L);
        basicDiscussionExample.setDescription("Description example");

        Discussion editedDiscussionExample = new Discussion();
        editedDiscussionExample.setId(1L);
        editedDiscussionExample.setDescription("Edited description");

        Mockito.when(this.discussionRepository.save(basicDiscussionExample)).thenReturn(editedDiscussionExample);
        Mockito.when(this.discussionRepository.findById(1L)).thenReturn(Optional.of(basicDiscussionExample));
        Discussion editedDiscussionTest = this.discussionService.edit(basicDiscussionExample);

        Mockito.verify(this.discussionRepository).save(basicDiscussionExample);

        assertEquals(editedDiscussionTest.getDescription(), editedDiscussionExample.getDescription());


    }

    @Test
    void whenGivenNewDiscussionShouldReturnNewDiscussion() {
        Discussion newDiscussionExample = new Discussion();
        newDiscussionExample.setId(1L);
        newDiscussionExample.setDescription("Description example");

        Mockito.when(this.discussionRepository.save(newDiscussionExample)).thenReturn(newDiscussionExample);

        this.discussionService.add(newDiscussionExample);

        Mockito.verify(this.discussionRepository).save(newDiscussionExample);

    }

    @Test
    void whenGivenIdShouldReturnDeletedDiscussion() {
        Discussion discussionExample = new Discussion();
        discussionExample.setId(1L);
        discussionExample.setDescription("Description example");

        Mockito.when(this.discussionRepository.findById(1L)).thenReturn(Optional.of(discussionExample));

        this.discussionService.delete(1L);

        Mockito.verify(this.discussionRepository).delete(discussionExample);
    }

    @Test
    void whenGivenFalseIdShouldThrowNoResultException() {
        Mockito.when(this.discussionRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(this.applicationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> {
            this.discussionService.browseByApplication(1L);
        });
        assertThrows(NoResultException.class, () -> {
            this.discussionService.read(1L);
        });

        Discussion discussionExample = new Discussion();
        discussionExample.setId(1L);

        assertThrows(NoResultException.class, () -> {
            this.discussionService.edit(discussionExample);
        });
        assertThrows(NoResultException.class, () -> {
            this.discussionService.delete(1L);
        });
    }
}