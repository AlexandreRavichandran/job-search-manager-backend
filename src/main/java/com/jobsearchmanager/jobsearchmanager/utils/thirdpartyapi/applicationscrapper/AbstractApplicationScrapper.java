package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper;

import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.ThirdpartyAPIManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public abstract class AbstractApplicationScrapper {

    protected String link = "";

    private Document getHTMLCode(String link) throws IOException {
        this.link = link;
        return Jsoup.connect(link).get();
    }

    abstract protected HashMap<String, String> retrieveData(Document document, Application emptyApplication);

    private Application generateApplication(HashMap<String, String> datas){
        Application application = new Application();
        application.setTitle(datas.get("title"));
        application.setDescription(datas.get("description"));
        application.setCompanyName(datas.get("companyName"));

        application.setMoved(false);
        application.setLink(this.link);
        application.setStatus(StatusEnum.GOING_TO_APPLY);
        application.setArchived(false);
        application.setResult(ResultEnum.NO_RESPONSE);

        return application;
    }

    public Application executeRequest(String link) throws IOException{
        Document document = this.getHTMLCode(link);
        return this.generateApplication(this.retrieveData(document, new Application()));
    }
}
