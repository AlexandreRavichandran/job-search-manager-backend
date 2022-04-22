package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper.IndeedScrapper;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper.MonsterScrapper;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper.PoleEmploiScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class ThirdpartyExecutor {

    @Autowired
    ThirdpartyAPIRegistry thirdpartyAPIRegistry;

    @Autowired
    IndeedScrapper indeedScrapper;

    @Autowired
    MonsterScrapper monsterScrapper;

    @Autowired
    PoleEmploiScrapper poleEmploiScrapper;


    @PostConstruct
    public void initThirdpartyCall(){
        this.thirdpartyAPIRegistry.registerApiService("indeed",this.indeedScrapper);
        this.thirdpartyAPIRegistry.registerApiService("monster",this.monsterScrapper);
        this.thirdpartyAPIRegistry.registerApiService("pole-emploi",this.poleEmploiScrapper);
    }

    public Application call(String name, String link) throws IOException {
        ThirdpartyAPIManager apiService = this.thirdpartyAPIRegistry.get(name);
        return apiService.executeRequest(link);
    }
}
