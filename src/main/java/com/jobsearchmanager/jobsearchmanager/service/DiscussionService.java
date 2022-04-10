package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.Discussion;

import javax.persistence.NoResultException;
import java.util.Collection;

public interface DiscussionService {

    Collection<Discussion> browseByApplication(Long applicationId) throws NoResultException;

    Discussion read(Long discussionId) throws NoResultException;

    Discussion edit(Long discussionToEdit) throws NoResultException;

    Discussion add(Discussion discussion);

    Discussion delete(Long discussionId) throws NoResultException;

}
