package com.jobsearchmanager.jobsearchmanager.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public abstract class AbstractStringToEnumConverter<E extends Enum<E>> implements Converter<String,E> {

    @Override
    public E convert(String source) {

        return E.valueOf(this.generateEnumClass(), source.toUpperCase());
    }

    abstract Class<E> generateEnumClass();
}
