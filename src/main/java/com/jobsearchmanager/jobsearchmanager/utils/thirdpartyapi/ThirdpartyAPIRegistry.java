package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi;

import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.ThirdpartyAPIManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ThirdpartyAPIRegistry {

    HashMap<String, ThirdpartyAPIManager> registry = new HashMap<>();

    public void registerApiService(String name, ThirdpartyAPIManager apiService){
        this.registry.put(name, apiService);
    }

    public ThirdpartyAPIManager get(String name){
        return this.registry.get(name);
    }
}
