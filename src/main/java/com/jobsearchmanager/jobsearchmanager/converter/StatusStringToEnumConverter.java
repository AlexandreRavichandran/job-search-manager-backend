package com.jobsearchmanager.jobsearchmanager.converter;

import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class StatusStringToEnumConverter extends AbstractStringToEnumConverter<StatusEnum>{

    @Override
    Class<StatusEnum> generateEnumClass() {
        return StatusEnum.class;
    }
}
