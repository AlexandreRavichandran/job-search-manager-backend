package com.jobsearchmanager.jobsearchmanager.converter;

import com.jobsearchmanager.jobsearchmanager.domain.DiscussionEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DiscussionStringToEnumConverterTest {

    private final DiscussionStringToEnumConverter converter = new DiscussionStringToEnumConverter();

    @Test
    void whenConvertingBasicStringShouldConvertToDiscussionEnum() {
        DiscussionEnum firstEnumTest = this.converter.convert("Physical meeting");
        DiscussionEnum secondEnumTest = this.converter.convert("PHYSICAL MEETING");
        assertEquals(DiscussionEnum.PHYSICAL_MEETING, firstEnumTest);
        assertEquals(DiscussionEnum.PHYSICAL_MEETING, secondEnumTest);
    }

    @Test
    void whenConvertingNonExistingEnumStringToDiscussionEnumShouldThrowException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            DiscussionEnum firstEnumTest = this.converter.convert("Non existant enum");
        });
    }
}