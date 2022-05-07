package com.jobsearchmanager.jobsearchmanager.converter;

import com.jobsearchmanager.jobsearchmanager.domain.DiscussionEnum;
import org.springframework.stereotype.Component;

@Component
public class DiscussionStringToEnumConverter extends AbstractStringToEnumConverter<DiscussionEnum>{

    @Override
    Class<DiscussionEnum> generateEnumClass() {
        return DiscussionEnum.class;
    }
}
