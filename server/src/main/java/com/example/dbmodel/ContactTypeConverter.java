package com.example.dbmodel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ContactTypeConverter implements AttributeConverter<ContactType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ContactType x) {
        return x != null ? x.getValue() : null;
    }

    @Override
    public ContactType convertToEntityAttribute(Integer y) {
        return y != null ? ContactType.valueOf(y) : null;
    }

}
