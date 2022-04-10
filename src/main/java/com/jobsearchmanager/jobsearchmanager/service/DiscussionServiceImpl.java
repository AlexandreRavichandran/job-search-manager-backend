package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.Discussion;
import com.jobsearchmanager.jobsearchmanager.repository.ApplicationRepository;
import com.jobsearchmanager.jobsearchmanager.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Override
    public Collection<Discussion> browseByApplication(Long applicationId) throws NoResultException {
        Application application = this.applicationRepository.findById(applicationId).orElseThrow(NoResultException::new);

        return this.discussionRepository.findByApplicationId(application.getId());
    }

    @Override
    public Discussion read(Long discussionId) throws NoResultException {
        return this.discussionRepository.findById(discussionId).orElseThrow(NoResultException::new);
    }

    @Override
    public Discussion edit(Discussion discussionToEdit) throws NoResultException {
        this.discussionRepository.findById(discussionToEdit.getId()).orElseThrow(NoResultException::new);

        return this.discussionRepository.save(discussionToEdit);
    }

    @Override
    public Discussion add(Discussion discussion) {

        return this.discussionRepository.save(discussion);
    }

    @Override
    public Discussion delete(Long discussionId) throws NoResultException {
        Discussion discussion = this.discussionRepository.findById(discussionId).orElseThrow(NoResultException::new);
        this.discussionRepository.delete(discussion);

        return discussion;

    }
}
