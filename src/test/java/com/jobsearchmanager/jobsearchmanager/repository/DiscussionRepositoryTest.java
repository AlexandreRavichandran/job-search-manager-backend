package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.Discussion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DiscussionRepositoryTest {

    @Autowired
    DiscussionRepository discussionRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    void shouldReturnByApplicationId() {
        Application application = new Application();
        application.setId(1L);

        this.applicationRepository.save(application);

        Discussion firstDiscussion = new Discussion();
        firstDiscussion.setId(2L);
        firstDiscussion.setRelatedApplication(application);
        Discussion secondDiscussion = new Discussion();
        secondDiscussion.setId(3L);
        secondDiscussion.setRelatedApplication(application);

        Collection<Discussion> discussions = Arrays.asList(firstDiscussion,secondDiscussion);
        this.discussionRepository.saveAll(discussions);
        Collection<Discussion> searchByApplication = this.discussionRepository.findByRelatedApplicationId(application.getId());

        assertTrue(searchByApplication.containsAll(discussions));
    }
}