package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi;

import com.jobsearchmanager.jobsearchmanager.domain.Application;

import java.io.IOException;

public interface ThirdpartyAPIManager {

    abstract Application executeRequest(String link) throws IOException;
}
