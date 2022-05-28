package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.ThirdpartyAPIManager;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PoleEmploiScrapper extends AbstractApplicationScrapper implements ThirdpartyAPIManager {

    @Override
    protected HashMap<String, String> retrieveData(Document document, Application emptyApplication) {

        String title = document.selectFirst("#labelPopinDetails").text();
        String description = document.selectFirst(".description").text();
        String companyName = document.selectFirst(".t4.title").text();

        HashMap<String, String> datas = new HashMap<>();

        datas.put("title",title);
        datas.put("description",description);
        datas.put("companyName",companyName);

        return datas;
    }
}
