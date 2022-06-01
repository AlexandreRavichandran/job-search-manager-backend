package com.jobsearchmanager.jobsearchmanager.converter;

import org.springframework.core.convert.converter.Converter;

public abstract class AbstractStringToEnumConverter<E extends Enum<E>> implements Converter<String, E> {

    @Override
    public E convert(String source) throws EnumConstantNotPresentException {
        try {
            return E.valueOf(this.generateEnumClass(), source.toUpperCase().replaceAll("\\s", "_"));
        } catch (Exception e) {
            throw new EnumConstantNotPresentException(this.generateEnumClass(), source);
        }

    }

    abstract Class<E> generateEnumClass();
}
