package com.jobsearchmanager.jobsearchmanager.converter;

import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import org.springframework.stereotype.Component;

@Component
public class ResultStringToEnumConverter extends AbstractStringToEnumConverter<ResultEnum>{

    @Override
    Class<ResultEnum> generateEnumClass() {
        return ResultEnum.class;
    }
}
