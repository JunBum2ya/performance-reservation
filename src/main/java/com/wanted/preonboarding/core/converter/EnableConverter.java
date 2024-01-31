package com.wanted.preonboarding.core.converter;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter
public class EnableConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean enable) {
        return enable ? "enable" : "disable";
    }

    @Override
    public Boolean convertToEntityAttribute(String str) {
        return "enable".equals(str);
    }
}
