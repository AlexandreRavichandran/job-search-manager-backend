package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ThirdpartyAPISelector {

    @Autowired
    ThirdpartyExecutor thirdpartyExecutor;

    public Application guessServiceByLink(String link) throws IOException {
        String relatedService = "";

        if (link.contains("indeed")) {
            relatedService = "indeed";
        } else if (link.contains("monster")) {
            relatedService = "monster";
        } else if (link.contains("pole-emploi")) {
            relatedService = "pole-emploi";
        }

        return this.thirdpartyExecutor.call(relatedService, link);
    }
}
