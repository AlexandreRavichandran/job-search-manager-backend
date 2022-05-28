package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.ThirdpartyAPIManager;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MonsterScrapper extends AbstractApplicationScrapper implements ThirdpartyAPIManager {

    @Override
    protected HashMap<String, String> retrieveData(Document document, Application emptyApplication) {

        String title = document.selectFirst(".JobViewTitle").text();
        String description = document.selectFirst(".djFvUg").text();
        String companyName = document.selectFirst(".dCOEYe").text();

        HashMap<String, String> datas = new HashMap<>();

        datas.put("title",title);
        datas.put("description",description);
        datas.put("companyName",companyName);

        return datas;
    }
}
