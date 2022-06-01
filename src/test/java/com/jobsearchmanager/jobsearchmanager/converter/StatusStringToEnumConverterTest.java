package com.jobsearchmanager.jobsearchmanager.converter;

import com.jobsearchmanager.jobsearchmanager.domain.StatusEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusStringToEnumConverterTest {

    private final StatusStringToEnumConverter converter = new StatusStringToEnumConverter();

    @Test
    void whenConvertingBasicStringShouldConvertToStatusEnum() {
        StatusEnum firstEnumTest = this.converter.convert("Going to apply");
        StatusEnum secondEnumTest = this.converter.convert("GOING_TO_APPLY");
        assertEquals(StatusEnum.GOING_TO_APPLY, firstEnumTest);
        assertEquals(StatusEnum.GOING_TO_APPLY, secondEnumTest);
    }

    @Test
    void whenConvertingNonExistingEnumStringToStatusEnumShouldThrowException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            StatusEnum firstEnumTest = this.converter.convert("Non existant enum");
        });
    }
}