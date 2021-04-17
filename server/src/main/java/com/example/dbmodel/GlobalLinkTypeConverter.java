package com.example.dbmodel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GlobalLinkTypeConverter implements AttributeConverter<GlobalLinkType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GlobalLinkType x) {
        return x != null ? x.getValue() : null;
    }

    @Override
    public GlobalLinkType convertToEntityAttribute(Integer y) {
        return y != null ? GlobalLinkType.valueOf(y) : null;
    }

}
