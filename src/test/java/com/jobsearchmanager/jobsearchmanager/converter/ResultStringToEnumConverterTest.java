package com.jobsearchmanager.jobsearchmanager.converter;

import com.jobsearchmanager.jobsearchmanager.domain.ResultEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultStringToEnumConverterTest {

    private final ResultStringToEnumConverter converter = new ResultStringToEnumConverter();

    @Test
    void whenConvertingBasicStringShouldConvertToResultEnum() {
        ResultEnum firstEnumTest = this.converter.convert("No response");
        ResultEnum secondEnumTest = this.converter.convert("NO RESPONSE");
        assertEquals(ResultEnum.NO_RESPONSE, firstEnumTest);
        assertEquals(ResultEnum.NO_RESPONSE, secondEnumTest);
    }

    @Test
    void whenConvertingNonExistingEnumStringToResultEnumShouldThrowException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            ResultEnum firstEnumTest = this.converter.convert("Non existant enum");
        });
    }
}